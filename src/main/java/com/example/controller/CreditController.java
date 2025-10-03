package com.example.controller;

import com.example.dto.CreditDTO;
import com.example.entity.Credit;
import com.example.mapper.CreditMapper;
import com.example.service.CreditService;

public class CreditController {

    public CreditService creditService;
    public CreditMapper creditMapper;


    public CreditController(CreditService creditService,CreditMapper creditMapper)
    {
        this.creditService = creditService;
        this.creditMapper = creditMapper;
    }


    public CreditDTO createCredit(CreditDTO creditDTO)
    {
      Credit credit =   this.creditMapper.TOEntity(creditDTO);
      creditService.createCredit(credit);
     return  this.creditMapper.ToDTO(credit);
    }
}
