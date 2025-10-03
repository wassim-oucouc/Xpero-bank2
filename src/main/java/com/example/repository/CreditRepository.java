package com.example.repository;

import com.example.entity.Credit;

public interface CreditRepository {

    public Credit createCredit(Credit credit);
    public Boolean checkCreditLate(String account_id);
}
