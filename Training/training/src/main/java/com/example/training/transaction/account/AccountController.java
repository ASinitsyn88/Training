package com.example.training.transaction.account;

import com.example.training.transaction.account.declarative.AccountServiceDecl;
import com.example.training.transaction.account.programmatically.txmanager.AccountServiceTxManager;
import com.example.training.transaction.account.programmatically.txtemplate.AccountServiceTxTemplate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountServiceDecl accountServiceDecl;
    private final AccountServiceTxTemplate accountServiceTxTemplate;
    private final AccountServiceTxManager accountServiceTxManager;

    // ----------------------- DECLARATIVE APPROACH -----------------------

    @PostMapping("balance")
    public ResponseEntity<Integer> addValueToAccountBalanceDecl(@Valid @RequestBody AccountBalance accountBalance) {
        int newBalance = accountServiceDecl.addValueToAccountBalance(accountBalance.getAccountId(), accountBalance.getValueToAdd());
        return ResponseEntity.ok(newBalance);
    }

    @PostMapping("balance2")
    public ResponseEntity<Integer> addValueToAccountBalanceInternalCallDecl(@Valid @RequestBody AccountBalance accountBalance) {
        int newBalance = accountServiceDecl.addValueToAccountBalance2(accountBalance.getAccountId(), accountBalance.getValueToAdd());
        return ResponseEntity.ok(newBalance);
    }

    // ----------------------- PROGRAMMATIC APPROACH (transactionTemplate) -----------------------

    @PostMapping("balance3")
    public ResponseEntity<Integer> addValueToAccountBalanceProg(@Valid @RequestBody AccountBalance accountBalance) {
        int newBalance = accountServiceTxTemplate.addValueToAccountBalance(accountBalance.getAccountId(), accountBalance.getValueToAdd());
        return ResponseEntity.ok(newBalance);
    }

    @PostMapping("balance4")
    public ResponseEntity<Integer> addValueToAccountBalanceInternalCallProg(@Valid @RequestBody AccountBalance accountBalance) {
        int newBalance = accountServiceTxTemplate.addValueToAccountBalance2(accountBalance.getAccountId(), accountBalance.getValueToAdd());
        return ResponseEntity.ok(newBalance);
    }

    // ----------------------- PROGRAMMATIC APPROACH (transactionManager) -----------------------

    @PostMapping("balance5")
    public ResponseEntity<Integer> addValueToAccountBalanceProg2(@Valid @RequestBody AccountBalance accountBalance) {
        int newBalance = accountServiceTxManager.addValueToAccountBalance(accountBalance.getAccountId(), accountBalance.getValueToAdd());
        return ResponseEntity.ok(newBalance);
    }

    @PostMapping("balance6")
    public ResponseEntity<Integer> addValueToAccountBalanceInternalCallProg2(@Valid @RequestBody AccountBalance accountBalance) {
        int newBalance = accountServiceTxManager.addValueToAccountBalance2(accountBalance.getAccountId(), accountBalance.getValueToAdd());
        return ResponseEntity.ok(newBalance);
    }
}