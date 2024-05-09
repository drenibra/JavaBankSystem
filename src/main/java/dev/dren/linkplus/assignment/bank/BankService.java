package dev.dren.linkplus.assignment.bank;

import dev.dren.linkplus.assignment.bank.Bank;
import dev.dren.linkplus.assignment.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public Bank saveBank(Bank bank) { return bankRepository.save(bank); }

    public Optional<Bank> getBankById(int id) {
        return bankRepository.findById(id);
    }

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public void updateBank(Bank bank, Integer id) {
        Optional<Bank> existingBank = bankRepository.findById(id);
        if (existingBank.isPresent()) {
            bankRepository.save(bank);
        }
    }

    public void deleteBank(int id) {
        bankRepository.deleteById(id);
    }

    public double getTotalTransactionFeeAmount(Integer id) {
        return bankRepository.findById(id).get().getTotalTransactionFeeAmount();
    }
    public double getTotalTransferAmount(Integer id) {
        return bankRepository.findById(id).get().getTotalTransferAmount();
    }
}
