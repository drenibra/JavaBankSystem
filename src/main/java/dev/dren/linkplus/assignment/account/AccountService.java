package dev.dren.linkplus.assignment.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> getAccountById(int id) {
        return accountRepository.findById(id);
    }

    public List<AccountDetailsDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> new AccountDetailsDTO(
                account.getAccountId(),
                account.getUserName(),
                account.getBalance(),
                account.getBank().getBankName()  // Accessing the bank name
        )).collect(Collectors.toList());
    }

    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

    public Double getAccountBalance(Integer id) {
        return accountRepository.findById(id).get().getBalance();
    }

    public synchronized void deposit(Integer id, double amount) {
        if (amount > 0) {
            Account account = accountRepository.findById(id).get();
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
    }

    public synchronized void withdraw(Integer id, double amount) {
        Account account = accountRepository.findById(id).get();
        if (amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }
    }
}
