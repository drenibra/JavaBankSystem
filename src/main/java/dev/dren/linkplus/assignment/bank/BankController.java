package dev.dren.linkplus.assignment.bank;

import dev.dren.linkplus.assignment.bank.Bank;
import dev.dren.linkplus.assignment.bank.BankService;
import dev.dren.linkplus.assignment.bank.BankService;
import dev.dren.linkplus.assignment.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PutMapping("/{id}")
    void update(@Valid @RequestBody Bank bank, @PathVariable Integer id) {
        bankService.updateBank(bank, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable int id) {
        bankService.deleteBank(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/totalTransactionFeeAmount/{id}")
    public double getTotalTransactionFeeAmount(@PathVariable Integer id) {
        return bankService.getTotalTransactionFeeAmount(id);
    }
    @GetMapping("/totalTransferAmount/{id}")
    public double getTotalTransferAmount(@PathVariable  Integer id) {
        return bankService.getTotalTransferAmount(id);
    }
}
