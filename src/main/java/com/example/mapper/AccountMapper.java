package com.example.mapper;

import com.example.dto.AccountDTO;
import com.example.dto.ClientDTO;
import com.example.entity.Account;

public class AccountMapper {

    public ClientMapper clientMapper;

    public AccountMapper(ClientMapper clientMapper)
    {
        this.clientMapper = clientMapper;
    }

    public Account ToEntity(AccountDTO accountDTO)
    {
        if(accountDTO == null)
        {
            return null;
        }

        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setClient(this.clientMapper.ToEntity(accountDTO.getClient()));
        account.setType(accountDTO.getType());
        account.setActive(accountDTO.getActive());
        account.setSolde(accountDTO.getSolde());
        account.setCreatedAt(accountDTO.getCreatedAt());
        account.setUpdatedAt(accountDTO.getUpdatedAt());

        return account;
    }

    public static AccountDTO TODTO(Account account)
    {
        if(account == null)
        {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setClient(ClientMapper.ToDTO(account.getClient()));
        accountDTO.setType(account.getType());
        accountDTO.setActive(account.getActive());
        accountDTO.setSolde(account.getSolde());
        accountDTO.setCreatedAt(account.getCreatedAt());
        accountDTO.setUpdatedAt(account.getUpdatedAt());

        return accountDTO;
    }
}
