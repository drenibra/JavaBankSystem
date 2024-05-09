package dev.dren.linkplus.assignment.bank;

import dev.dren.linkplus.assignment.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Account, Integer> {
}
