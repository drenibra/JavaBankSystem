package dev.dren.linkplus.assignment.bank;

import dev.dren.linkplus.assignment.bank.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public Optional<Bank> getBankById(int id) {
        return bankRepository.findById(id);
    }

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public void deleteBank(int id) {
        bankRepository.deleteById(id);
    }
}
