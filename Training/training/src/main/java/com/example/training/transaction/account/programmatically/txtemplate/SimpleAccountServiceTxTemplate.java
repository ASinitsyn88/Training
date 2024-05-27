package com.example.training.transaction.account.programmatically.txtemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleAccountServiceTxTemplate implements AccountServiceTxTemplate {
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    @Override
    public Integer addValueToAccountBalance(Integer accountId, Integer valueToAdd) {
        return transactionTemplate.execute(status -> {
            log.info("TX IN addValueToAccountBalance >>>");
            Integer amount = jdbcTemplate.queryForObject("SELECT c_balance FROM t_account WHERE id = ?", Integer.class, accountId);
            jdbcTemplate.update("UPDATE t_account SET c_balance = ? WHERE id = ?", amount + valueToAdd, accountId);
            log.info("<<< TX OUT addValueToAccountBalance");
            return amount + valueToAdd;
        });
    }

    @Override
    public Integer addValueToAccountBalance2(Integer accountId, Integer valueToAdd) {
        return transactionTemplate.execute(status -> {
            log.info("TX IN addValueToAccountBalance2 >>>");
            int newBalance = this.addValueToAccountBalance(accountId, valueToAdd);
            log.info("<<< TX OUT addValueToAccountBalance2");
            return newBalance;
        });
    }
}