package dev.dren.linkplus.assignment.bank;

import dev.dren.linkplus.assignment.bank.Bank;
import dev.dren.linkplus.assignment.bank.BankService;
import dev.dren.linkplus.assignment.bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping
    public Bank createBank(@RequestBody Bank bank) {
        return bankService.saveBank(bank);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable int id) {
        return bankService.getBankById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Bank> getAllBanks() {
        return bankService.getAllBanks();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable int id) {
        bankService.deleteBank(id);
        return ResponseEntity.noContent().build();
    }
}
