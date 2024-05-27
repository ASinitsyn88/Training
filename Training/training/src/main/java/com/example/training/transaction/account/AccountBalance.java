package com.example.training.transaction.account;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountBalance {
    @NotNull(message = "accountId cannot be null")
    private Integer accountId;
    @NotNull(message = "valueToAdd cannot be null")
    private Integer valueToAdd;
}