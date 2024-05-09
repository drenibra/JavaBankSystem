package dev.dren.linkplus.assignment.account;

public class DepositWithdrawDTO {
    public Integer accountId;
    public double amount;

    public DepositWithdrawDTO(Integer accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }
}
