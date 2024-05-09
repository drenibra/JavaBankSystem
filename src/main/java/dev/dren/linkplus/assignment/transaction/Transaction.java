package dev.dren.linkplus.assignment.transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {
    public enum TransactionReason {
        TRANSFER,
        WITHDRAWAL,
        DEPOSIT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    private double amount;
    private int originatingAccountId;
    private int resultingAccountId;
    private TransactionReason transactionReason;

    protected Transaction() {

    }

    public Transaction(double amount, int originatingAccountId, int resultingAccountId, TransactionReason transactionReason) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        this.amount = amount;
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.transactionReason = transactionReason;
    }

    public double getAmount() {
        return amount;
    }

    public int getOriginatingAccountId() {
        return originatingAccountId;
    }

    public int getResultingAccountId() {
        return resultingAccountId;
    }

    public TransactionReason getTransactionReason() {
        return transactionReason;
    }
}
