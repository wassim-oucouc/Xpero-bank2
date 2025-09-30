package com.example.service;

import com.example.entity.Transaction;
import com.example.repository.TransactionRepository;

public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository)
    {
        this.transactionRepository = transactionRepository;
    }


    public Transaction createTransactionDeposit(Transaction transaction)
    {
        return this.transactionRepository.createTransaction(transaction);
    }
}
