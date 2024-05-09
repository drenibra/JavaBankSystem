package dev.dren.linkplus.assignment.transaction;

public class TransactionDTO {
    public double amount;
    public int originatingAccountId;
    public int resultingAccountId;
    public Transaction.TransactionReason transactionReason;
    public TransactionService.TransactionType transactionType;

    public TransactionDTO(double amount, int originatingAccountId, int resultingAccountId, Transaction.TransactionReason transactionReason, TransactionService.TransactionType transactionType) {
        this.amount = amount;
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.transactionReason = transactionReason;
        this.transactionType = transactionType;
    }
}
