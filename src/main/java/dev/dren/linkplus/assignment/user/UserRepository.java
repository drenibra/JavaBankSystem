package dev.dren.linkplus.assignment.user;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    List<User> findAll() {
        return users;
    }

    Optional<User> findById(Integer id) {
        return users.stream()
                .filter(user -> user.id() == id)
                .findFirst();
    }

    void create(@Valid User user) {
        users.add(user);
    }

    void update(User user, Integer id) {
        Optional<User> existingUser = findById(id);
//        existingUser.ifPresent(value -> users.set(users.indexOf(value), user));
        if (existingUser.isPresent()) {
            users.set(users.indexOf(existingUser.get()), user);
        }
    }

    void delete(Integer id) {
        users.removeIf(user -> user.id() == id);
    }

    @PostConstruct
    private void init() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            users.add(new User(1, "Dren", "Ibrahimi", dateFormat.parse("2002-09-04")));
            users.add(new User(2, "Art", "Ibrahimi", dateFormat.parse("1999-03-13")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
