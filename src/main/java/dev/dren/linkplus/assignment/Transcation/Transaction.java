package dev.dren.linkplus.assignment.Transcation;

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

    public Transaction(Integer transactionId, double amount, int originatingAccountId, int resultingAccountId, TransactionReason transactionReason) {
        this.transactionId = transactionId;
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
