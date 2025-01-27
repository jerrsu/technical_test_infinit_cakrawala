package com.technical.test.repository;

import com.technical.test.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jerry
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUsername(String username);
}
