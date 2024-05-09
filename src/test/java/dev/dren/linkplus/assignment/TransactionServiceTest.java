package dev.dren.linkplus.assignment;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import dev.dren.linkplus.assignment.account.Account;
import dev.dren.linkplus.assignment.account.AccountRepository;
import dev.dren.linkplus.assignment.bank.Bank;
import dev.dren.linkplus.assignment.bank.BankRepository;
import dev.dren.linkplus.assignment.transaction.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private BankRepository bankRepository;

    @Test
    public void testGetTransactionsOfAccount() {
        Transaction transaction = new Transaction(100, 1, 2, Transaction.TransactionReason.TRANSFER);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> result = transactionService.getTransactionsOfAccount(1);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.get(0).getOriginatingAccountId());
    }

    @Test
    public void testPerformTransaction() throws Exception {
        Bank bank = new Bank("Raiffeisen", 10, 0.01);
        Account originatingAccount = new Account(1, "User1", 1000.00, bank);
        Account resultingAccount = new Account(2, "User2", 500.00, bank);

        when(accountRepository.findById(1)).thenReturn(Optional.of(originatingAccount));
        when(accountRepository.findById(2)).thenReturn(Optional.of(resultingAccount));
        when(bankRepository.findById(originatingAccount.getBank().getBankId())).thenReturn(Optional.of(bank));

        TransactionDTO dto = new TransactionDTO(100, 1, 2, Transaction.TransactionReason.TRANSFER, TransactionService.TransactionType.FLAT_FEE);

        Transaction transaction = transactionService.performTransaction(dto);

        assertNotNull(transaction);
        assertEquals(890.0, originatingAccount.getBalance());
        assertEquals(600.0, resultingAccount.getBalance());
        verify(accountRepository, times(4)).save(any(Account.class));
    }
}
