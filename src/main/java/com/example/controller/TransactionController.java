package com.example.controller;

import com.example.dto.TransactionDTO;
import com.example.entity.Transaction;
import com.example.mapper.TransactionMapper;
import com.example.service.TransactionService;

public class TransactionController {

    private TransactionService transactionService;
    private TransactionMapper transactionMapper;

    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper)
    {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }


    public TransactionDTO createTransaction(TransactionDTO transactionDTO)
    {
        Transaction transaction =  this.transactionMapper.ToEntity(transactionDTO);
        this.transactionService.createTransactionDeposit(transaction);

        return this.transactionMapper.ToDTO(transaction);
    }
}
