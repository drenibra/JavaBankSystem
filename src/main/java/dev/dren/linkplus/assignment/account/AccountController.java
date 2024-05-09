package dev.dren.linkplus.assignment.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable int id) {
        return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/checkBalance/{id}")
    public Double checkBalance(@PathVariable int id) {
        return accountService.getAccountBalance(id);
    }

    @GetMapping
    public List<AccountDetailsDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deletion successful!");
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositWithdrawDTO depositWithdrawDTO) {
        accountService.deposit(depositWithdrawDTO.accountId, depositWithdrawDTO.amount);
        return ResponseEntity.ok("Deposit successful!");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody DepositWithdrawDTO depositWithdrawDTO) {
        accountService.withdraw(depositWithdrawDTO.accountId, depositWithdrawDTO.amount);
        return ResponseEntity.ok("Withdrawal successful!");
    }
}
