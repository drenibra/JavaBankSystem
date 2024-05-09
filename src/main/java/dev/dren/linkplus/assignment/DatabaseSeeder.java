package dev.dren.linkplus.assignment;

import dev.dren.linkplus.assignment.account.Account;
import dev.dren.linkplus.assignment.account.AccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {

    private final AccountRepository accountRepository;

    public DatabaseSeeder(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
    }
}