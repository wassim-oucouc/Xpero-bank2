package com.example.mapper;

import com.example.dto.TransactionDTO;
import com.example.entity.Transaction;

public class TransactionMapper {

    public Fee_ruleMapper feeRuleMapper;
    public AccountMapper accountMapper;


    public TransactionMapper(AccountMapper accountMapper)
    {
        this.accountMapper = accountMapper;
    }

    public TransactionMapper()
    {

    }



    public Transaction ToEntity(TransactionDTO transactionDTO)
    {
        if(transactionDTO == null)
        {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setId(transactionDTO.getId());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setStatus(transactionDTO.getStatus());
        transaction.setTransferIn(this.accountMapper.ToEntity(transactionDTO.getTransferIn()));
        transaction.setTransferOut(this.accountMapper.ToEntity(transactionDTO.getTransferOut()));
        transaction.setCreatedAt(transactionDTO.getCreatedAt());
        transaction.setUpdatedAt(transactionDTO.getUpdatedAt());

        return transaction;
    }

    public TransactionDTO ToDTO(Transaction transaction)
    {
        if(transaction == null)
        {
            return null;
        }

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setType(transaction.getType());
        transactionDTO.setStatus(transaction.getStatus());
        transactionDTO.setFeeRule(this.feeRuleMapper.ToDTO(transaction.getFeeRule()));
        transactionDTO.setTransferIn(AccountMapper.TODTO(transaction.getTransferIn()));
        transactionDTO.setCreatedAt(transactionDTO.getCreatedAt());
        transactionDTO.setUpdatedAt(transactionDTO.getUpdatedAt());

        return transactionDTO;
    }
}
