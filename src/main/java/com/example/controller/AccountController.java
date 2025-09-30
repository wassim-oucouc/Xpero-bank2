package com.example.controller;

import com.example.dto.AccountDTO;
import com.example.entity.Account;
import com.example.mapper.AccountMapper;
import com.example.service.AccountService;

import java.math.BigDecimal;
import java.util.List;

public class AccountController {

    public AccountService accountService;
    public AccountMapper accountMapper;


    public AccountController(AccountService accountService,AccountMapper accountMapper)
    {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }


    public AccountDTO createAccount(AccountDTO accountDTO)
    {
        Account account = this.accountMapper.ToEntity(accountDTO);
      Account accountCreated =   this.accountService.createAccount(account);
      return AccountMapper.TODTO(accountCreated);
    }

    public List<AccountDTO> getAllAccounts()
    {
        List<Account> accounts = this.accountService.getAllAccounts();
       return  accounts.stream().map(AccountMapper::TODTO).toList();
    }

    public Boolean closeAccount(String account_number)
    {
        return this.accountService.closeAccountById(account_number);
    }

    public Account getAccountByNumber(String account_number)
    {
        return this.accountService.getAccountByNumber(account_number);
    }

    public Boolean depositArgent(String account_number, BigDecimal montant)
    {
        return this.accountService.depositArgentByAccountId(account_number,montant);
    }



}
