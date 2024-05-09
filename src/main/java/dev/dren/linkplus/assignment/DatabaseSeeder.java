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

    private Bank bank1;
    private Bank bank2;

    public DatabaseSeeder(AccountRepository accountRepository, BankRepository bankRepository) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }

    @PostConstruct
    public void init() {
        if (bankRepository.count() == 0) {
            try {
                bank1 = new Bank("Raiffeisen",1, 0.03);
                bank2 = new Bank("Procredit",0, 0.05);
                bankRepository.save(bank1);
                bankRepository.save(bank2);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if (accountRepository.count() == 0) {
            try {
                Account dren = new Account(1, "Dren", 250, bank1);
                Account art = new Account(2, "Art", 200, bank2);
                accountRepository.save(dren);
                accountRepository.save(art);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}