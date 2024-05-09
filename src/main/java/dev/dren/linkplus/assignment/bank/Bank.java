package dev.dren.linkplus.assignment.bank;

import dev.dren.linkplus.assignment.Transcation.Transaction;
import dev.dren.linkplus.assignment.account.Account;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bankId;
    private String bankName;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Account> accounts;

    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFeeAmount;
    private double transactionPercentFeeValue;

    protected Bank() {

    }

    public Bank(String bankName, double transactionFlatFeeAmount, double transactionPercentFeeValue) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.totalTransactionFeeAmount = 0;
        this.totalTransferAmount = 0;
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
        this.transactionPercentFeeValue = transactionPercentFeeValue;
    }

    public String getBankName() {
        return bankName;
    }

    public double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public double getTransactionFlatFeeAmount() {
        return transactionFlatFeeAmount;
    }

    public void setTransactionFlatFeeAmount(double transactionFlatFeeAmount) {
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
    }

    public double getTransactionPercentFeeValue() {
        return transactionPercentFeeValue;
    }

    public void setTransactionPercentFeeValue(double transactionPercentFeeValue) {
        this.transactionPercentFeeValue = transactionPercentFeeValue;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

//    // Method to perform a transaction (transfer, withdrawal, deposit)
//    public void performTransaction(Transaction transaction) {
//        double transactionAmount = transaction.getAmount();
//        double feeAmount = calculateTransactionFee(transactionAmount);
//
//        // Deduct the fee from the transaction amount
//        double netTransactionAmount = transactionAmount - feeAmount;
//
//        // Update bank statistics
//        totalTransactionFeeAmount += feeAmount;
//        totalTransferAmount += netTransactionAmount;
//
//        // Find originating and resulting accounts
//        Account originatingAccount = findAccountById(transaction.getOriginatingAccountId());
//        Account resultingAccount = findAccountById(transaction.getResultingAccountId());
//
//        if (originatingAccount == null || resultingAccount == null) {
//            throw new IllegalArgumentException("Originating or resulting account not found");
//        }
//
//        // Perform the transaction based on the transaction reason
//        switch (transaction.getTransactionReason()) {
//            case TRANSFER:
//                if (originatingAccount.getBalance() < transactionAmount) {
//                    throw new IllegalArgumentException("Not enough funds for transfer");
//                }
//                originatingAccount.withdraw(transactionAmount);
//                resultingAccount.deposit(netTransactionAmount);
//                break;
//            case WITHDRAWAL:
//                if (originatingAccount.getBalance() < transactionAmount) {
//                    throw new IllegalArgumentException("Not enough funds for withdrawal");
//                }
//                originatingAccount.withdraw(transactionAmount);
//                break;
//            case DEPOSIT:
//                resultingAccount.deposit(netTransactionAmount);
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid transaction reason");
//        }
//    }
//
//    // Method to calculate transaction fee based on the transaction amount
//    private double calculateTransactionFee(double transactionAmount) {
//        return transactionAmount * transactionPercentFeeValue + transactionFlatFeeAmount;
//    }
}
