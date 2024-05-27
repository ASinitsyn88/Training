package com.example.training.transaction.account.declarative;

import org.springframework.transaction.annotation.Transactional;

public interface AccountServiceDecl {

    @Transactional
    Integer addValueToAccountBalance(Integer accountId, Integer valueToAdd);

    @Transactional
    Integer addValueToAccountBalance2(Integer accountId, Integer valueToAdd);
}