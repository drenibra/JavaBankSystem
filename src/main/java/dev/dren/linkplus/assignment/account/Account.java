package dev.dren.linkplus.assignment.account;

import dev.dren.linkplus.assignment.bank.Bank;
import dev.dren.linkplus.assignment.user.User;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;
    private String userName;
    private double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    protected Account() {

    }

    public Account(Integer accountId, String userName, double initialBalance, Bank bank) {
        this.accountId = accountId;
        this.userName = userName;
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
        this.bank = bank;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public String getUserName() {
        return userName;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public Bank getBank() {
        return this.bank;
    }

    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
    }

    public synchronized void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }
    }
}
