package com.example.training.transaction.account.programmatically.txtemplate;

public interface AccountServiceTxTemplate {

    Integer addValueToAccountBalance(Integer accountId, Integer valueToAdd);

    Integer addValueToAccountBalance2(Integer accountId, Integer valueToAdd);
}