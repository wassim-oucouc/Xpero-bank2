package com.example.service;

import com.example.entity.Credit;
import com.example.repository.CreditRepository;

import java.math.BigDecimal;

public class CreditService {


    public CreditRepository creditRepository;

    public CreditService(CreditRepository creditRepository)
    {
        this.creditRepository = creditRepository;
    }



    public Credit createCredit(Credit credit)
    {
        return this.creditRepository.createCredit(credit);
    }

    public Boolean checkCreditLate(String account_id)
    {
        return this.creditRepository.checkCreditLate(account_id);
    }

    public BigDecimal CalculateCreditClient(String account_id)
    {
        return this.creditRepository.calculateTotalMonthlyAmountByAccount(account_id);
    }
}
