package com.example.controller;

import com.example.dto.AccountDTO;
import com.example.dto.TransactionDTO;
import com.example.entity.Account;
import com.example.entity.Transaction;
import com.example.mapper.AccountMapper;
import com.example.mapper.TransactionMapper;
import com.example.service.AccountService;
import com.example.service.TransactionService;

import java.math.BigDecimal;
import java.util.List;

public class AccountController {

    public AccountService accountService;
    public AccountMapper accountMapper;
    public TransactionService transactionService;
    public TransactionMapper transactionMapper;


    public AccountController(AccountService accountService,AccountMapper accountMapper,TransactionService transactionService,TransactionMapper transactionMapper)
    {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
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

    public Boolean WithdrawArgent(String account_number,BigDecimal montant)
    {
        return this.accountService.withdrawArgentByAccountId(account_number,montant);
    }

    public void TransferArgent(String accountNumberDebité, String accountNumberdéstinataire, BigDecimal montant, TransactionDTO transactionDTO)
    {
         this.accountService.depositArgentByAccountId(accountNumberdéstinataire,montant);
         this.accountService.withdrawArgentByAccountId(accountNumberDebité,montant);
        Transaction transaction = this.transactionMapper.ToEntity(transactionDTO);
         this.transactionService.createTransactionTransfer(transaction);
    }




}
