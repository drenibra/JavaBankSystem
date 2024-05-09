package dev.dren.linkplus.assignment.transaction;

import dev.dren.linkplus.assignment.account.Account;
import dev.dren.linkplus.assignment.account.AccountRepository;
import dev.dren.linkplus.assignment.bank.Bank;
import dev.dren.linkplus.assignment.bank.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    //    @Autowired
//    private TransactionRepository transactionRepository;
//
//    public Transaction saveTransaction(Transaction transaction) {
//        return transactionRepository.save(transaction);
//    }
//
//    public Optional<Transaction> getTransactionById(int id) {
//        return transactionRepository.findById(id);
//    }
//
//    public List<Transaction> getAllTransactions() {
//        return transactionRepository.findAll();
//    }
//
//    public void deleteTransaction(int id) {
//        transactionRepository.deleteById(id);
//    }
    public enum TransactionType {
        FLAT_FEE,
        PERCENT_FEE
    }
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BankRepository bankRepository;

    public Transaction performTransaction(TransactionDTO transaction) {
        var originatingAccountId = transaction.originatingAccountId;
        var resultingAccountId = transaction.resultingAccountId;
        var amount = transaction.amount;
        var transactionType = transaction.transactionType;
        var transactionReason = transaction.transactionReason;

        Account originatingAccount = accountRepository.findById(originatingAccountId).orElseThrow(IllegalArgumentException::new);
        Account resultingAccount = accountRepository.findById(resultingAccountId).orElseThrow(IllegalArgumentException::new);
        Bank originatingBank = bankRepository.findById(originatingAccount.getBank().getBankId()).orElseThrow(IllegalArgumentException::new);

        double fee = calculateFee(amount, transactionType, originatingBank);
        double totalAmount = amount + fee;

        if (originatingAccount.getBalance() < totalAmount) {
            throw new InsufficientFundsException("Insufficient funds in the originating account");
        }

        originatingAccount.setBalance(originatingAccount.getBalance() - totalAmount);
        resultingAccount.setBalance(resultingAccount.getBalance() + amount);

        accountRepository.save(originatingAccount);
        accountRepository.save(resultingAccount);

        return new Transaction(amount, originatingAccountId, resultingAccountId, transactionReason);
    }

    private double calculateFee(double amount, TransactionType transactionType, Bank bank) {
        if (transactionType == TransactionType.FLAT_FEE) {
            return bank.getTransactionFlatFeeAmount();
        } else if (transactionType == TransactionType.PERCENT_FEE) {
            return amount * bank.getTransactionPercentFeeValue();
        }
        return 0.0;
    }
}
