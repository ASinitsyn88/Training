package com.example.training.transaction.account.programmatically.txmanager;

public interface AccountServiceTxManager {

    Integer addValueToAccountBalance(Integer accountId, Integer valueToAdd);

    Integer addValueToAccountBalance2(Integer accountId, Integer valueToAdd);
}