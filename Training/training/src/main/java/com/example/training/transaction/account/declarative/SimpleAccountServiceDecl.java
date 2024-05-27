package com.example.training.transaction.account.declarative;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleAccountServiceDecl implements AccountServiceDecl {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Integer addValueToAccountBalance(Integer accountId, Integer valueToAdd) {
        log.info("TX IN addValueToAccountBalance >>>");
        Integer amount = jdbcTemplate.queryForObject("SELECT c_balance FROM t_account WHERE id = ?", Integer.class, accountId);
        jdbcTemplate.update("UPDATE t_account SET c_balance = ? WHERE id = ?", amount + valueToAdd, accountId);
        log.info("<<< TX OUT addValueToAccountBalance");
        return amount + valueToAdd;
    }

    /**
     * Pay attention!
     * In this case only one transaction for addValueToAccountBalance2 will be created
     * new transaction won't be created for this.addValueToAccountBalance
     * Transactions are created only when called from outside the class SimpleAccountService
     * Internal call doesn't create a new transaction
     */
    @Override
    public Integer addValueToAccountBalance2(Integer accountId, Integer valueToAdd) {
        log.info("TX IN addValueToAccountBalance2 >>>");
        int newBalance = this.addValueToAccountBalance(accountId, valueToAdd);
        log.info("<<< TX OUT addValueToAccountBalance2");
        return newBalance;
    }
}