package dev.dren.linkplus.assignment.transaction;

import dev.dren.linkplus.assignment.account.Account;
import dev.dren.linkplus.assignment.account.AccountRepository;
import dev.dren.linkplus.assignment.bank.Bank;
import dev.dren.linkplus.assignment.bank.BankRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<Transaction> getTransactionsOfAccount(Integer id) {
        List<Transaction> transactions = transactionRepository.findAll()
                .stream()
                .filter(transaction -> transaction.getOriginatingAccountId() == (id))
                .collect(Collectors.toList());
        return transactions;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public enum TransactionType {
        FLAT_FEE,
        PERCENT_FEE
    }
    @Autowired
    private BankRepository bankRepository;

    public Transaction performTransaction(TransactionDTO transaction) {
        var originatingAccountId = transaction.originatingAccountId;
        var resultingAccountId = transaction.resultingAccountId;
        var amount = transaction.amount;
        var transactionType = transaction.transactionType;
        var transactionReason = transaction.transactionReason;

        Account originatingAccount = accountRepository.findById(originatingAccountId).orElseThrow(EntityNotFoundException::new);
        Bank originatingBank = bankRepository.findById(originatingAccount.getBank().getBankId()).orElseThrow(EntityNotFoundException::new);

        double fee = transactionReason == Transaction.TransactionReason.TRANSFER ? calculateFee(amount, transactionType, originatingAccount.getBank()) : 0;
        double totalAmount = amount + fee;

        originatingBank.setTotalTransferAmount(originatingBank.getTotalTransferAmount() + amount);
        originatingBank.setTotalTransactionFeeAmount(originatingBank.getTotalTransactionFeeAmount() + fee);

        if (transactionReason != Transaction.TransactionReason.DEPOSIT && originatingAccount.getBalance() < totalAmount) {
            throw new InsufficientFundsException("Insufficient funds in the originating account");
        }

        switch (transactionReason) {
            case TRANSFER:
                Account resultingAccount = accountRepository.findById(resultingAccountId).orElseThrow(EntityNotFoundException::new);
                if (originatingAccount.getBalance() < totalAmount) {
                    throw new InsufficientFundsException("Insufficient funds for transfer.");
                }

                originatingAccount.setBalance(originatingAccount.getBalance() - totalAmount);
                resultingAccount.setBalance(resultingAccount.getBalance() + amount);

                accountRepository.save(originatingAccount);
                accountRepository.save(resultingAccount);
                break;

            case WITHDRAWAL:
                if (originatingAccount.getBalance() < totalAmount) {
                    throw new InsufficientFundsException("Insufficient funds for withdrawal.");
                }
                originatingAccount.setBalance(originatingAccount.getBalance() - totalAmount);
                accountRepository.save(originatingAccount);
                break;

            case DEPOSIT:
                originatingAccount.setBalance(originatingAccount.getBalance() + amount);
                accountRepository.save(originatingAccount);
                break;
        }

        Transaction resultingTransaction = new Transaction(amount, originatingAccountId, resultingAccountId, transactionReason);
        transactionRepository.save(resultingTransaction);

        return resultingTransaction;
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
