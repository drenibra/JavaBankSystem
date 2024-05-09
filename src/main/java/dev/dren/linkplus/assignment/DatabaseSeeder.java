package dev.dren.linkplus.assignment;

import dev.dren.linkplus.assignment.account.Account;
import dev.dren.linkplus.assignment.account.AccountRepository;
import dev.dren.linkplus.assignment.bank.Bank;
import dev.dren.linkplus.assignment.bank.BankRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {

    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;

    public DatabaseSeeder(AccountRepository accountRepository, BankRepository bankRepository) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }

    @PostConstruct
    public void init() {
        if (accountRepository.count() == 0) { // Check if repository is empty
            try {
                accountRepository.save(new Account(1, "Dren", 250));
                accountRepository.save(new Account(2, "Art", 200));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if (bankRepository.count() == 0) { // Check if repository is empty
            try {
                bankRepository.save(new Bank("Raiffeisen",1, 3));
                bankRepository.save(new Bank("Prodcredit",0, 5));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}