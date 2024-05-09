package dev.dren.linkplus.assignment.account;

import dev.dren.linkplus.assignment.bank.Bank;

public class AccountDetailsDTO {
    public Integer accountId;
    public String userName;
    public double balance;
    public String bankName;

    public AccountDetailsDTO(Integer accountId, String userName, double balance, String bankName) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = balance;
        this.bankName = bankName;
    }
}
