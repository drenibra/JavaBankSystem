package dev.dren.linkplus.assignment.bank;

import dev.dren.linkplus.assignment.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private AccountRepository accountRepository;

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> getAccountById(int id) {
        return accountRepository.findById(id);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }
}