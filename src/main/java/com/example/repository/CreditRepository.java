package com.example.repository;

import com.example.entity.Credit;

import java.math.BigDecimal;

public interface CreditRepository {

    public Credit createCredit(Credit credit);
    public Boolean checkCreditLate(String account_id);
    public BigDecimal calculateTotalMonthlyAmountByAccount(String account_id);
}
