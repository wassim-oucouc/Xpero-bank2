package com.example.mapper;

import com.example.dto.CreditDTO;
import com.example.entity.Credit;

public class CreditMapper {


    public AccountMapper accountMapper;


    public CreditMapper(AccountMapper accountMapper)
    {
        this.accountMapper = accountMapper;
    }


    public CreditDTO ToDTO(Credit credit)
    {
        CreditDTO creditCreated = new CreditDTO();
        creditCreated.setId(credit.getId());
        creditCreated.setCreditStatus(credit.getCreditStatus());
        creditCreated.setCreditType(credit.getCreditType());
        creditCreated.setAccount(AccountMapper.TODTO(credit.getAccount()));
        creditCreated.setAmount(credit.getAmount());
        creditCreated.setDuree(credit.getDuree());
        creditCreated.setFeeRule(credit.getFeeRule());
        creditCreated.setTaux(credit.getTaux());
        creditCreated.setCreated_at(credit.getCreated_at());
        creditCreated.setUpdated_at(credit.getUpdated_at());

        return creditCreated;

    }

    public Credit TOEntity(CreditDTO creditDTO)
    {
        Credit credit = new Credit();
        credit.setId(creditDTO.getId());
        credit.setAccount(this.accountMapper.ToEntity(creditDTO.getAccount()));
        credit.setAmount(creditDTO.getAmount());
        credit.setDuree(creditDTO.getDuree());
        credit.setCreditType(creditDTO.getCreditType());
        credit.setFeeRule(creditDTO.getFeeRule());
        credit.setIs_active(creditDTO.getIs_active());
        credit.setJustification(creditDTO.getJustification());
        credit.setTaux(creditDTO.getTaux());
        credit.setCreditStatus(creditDTO.getCreditStatus());
        credit.setCreated_at(creditDTO.getCreated_at());


        return credit;

    }

}
