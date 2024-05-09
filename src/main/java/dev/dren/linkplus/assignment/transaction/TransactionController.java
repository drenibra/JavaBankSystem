package dev.dren.linkplus.assignment.transaction;

import dev.dren.linkplus.assignment.transaction.Transaction;
import dev.dren.linkplus.assignment.transaction.TransactionRepository;
import dev.dren.linkplus.assignment.transaction.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping
    public Transaction performTransaction(@RequestBody @Valid final TransactionDTO transaction) {
        return transactionService.performTransaction(transaction);
    }

    @GetMapping("/originAccount/{id}")
    public List<Transaction> getTransactionsOfAccount(@PathVariable Integer id) {
        return transactionService.getTransactionsOfAccount(id);
    }
}
