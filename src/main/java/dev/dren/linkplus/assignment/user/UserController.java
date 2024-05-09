package dev.dren.linkplus.assignment.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
         this.userRepository = userRepository;
    }

    @GetMapping("")
    List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    User findById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create (@Valid @RequestBody User user) {
        userRepository.create(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody User user, @PathVariable Integer id) {
        userRepository.update(user, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        userRepository.delete(id);
    }
}