package com.example.training.transaction.account.programmatically.txmanager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleAccountServiceTxManager implements AccountServiceTxManager {
    private final JdbcTemplate jdbcTemplate;
    private final PlatformTransactionManager transactionManager;

    @Override
    public Integer addValueToAccountBalance(Integer accountId, Integer valueToAdd) {
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());
        log.info("TX IN addValueToAccountBalance >>>");
        Integer amount = jdbcTemplate.queryForObject("SELECT c_balance FROM t_account WHERE id = ?", Integer.class, accountId);
        jdbcTemplate.update("UPDATE t_account SET c_balance = ? WHERE id = ?", amount + valueToAdd, accountId);
        log.info("<<< TX OUT addValueToAccountBalance");
        transactionManager.commit(transaction);
        return amount + valueToAdd;
    }

    @Override
    public Integer addValueToAccountBalance2(Integer accountId, Integer valueToAdd) {
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());
        log.info("TX IN addValueToAccountBalance2 >>>");
        int newBalance = this.addValueToAccountBalance(accountId, valueToAdd);
        log.info("<<< TX OUT addValueToAccountBalance2");
        transactionManager.commit(transaction);
        return newBalance;
    }
}