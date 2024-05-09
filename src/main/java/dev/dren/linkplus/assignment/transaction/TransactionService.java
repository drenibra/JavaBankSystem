package dev.dren.linkplus.assignment.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransactionById(int id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void deleteTransaction(int id) {
        transactionRepository.deleteById(id);
    }
}
