package com.example.service;

import com.example.entity.Account;
import com.example.entity.Client;
import com.example.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.List;

public class AccountService {

    public AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Account createAccount(Account account) {
        return this.accountRepository.createAccount(account);
    }

    public Boolean checkAccountTypeClient(int type_id, String cin) {
        return this.accountRepository.checkAccountTypeClient(type_id, cin);
    }

    public String getIdAccountTypeByString(int type_id) {
        return this.accountRepository.getIdAccountTypeByString(type_id);
    }

    public List<Account> getAllAccounts() {
        return this.accountRepository.getAllAccounts();
    }

    public Boolean checkAccountExists(String acccount_number) {
        return this.accountRepository.checkAccountExists(acccount_number);
    }

    public Boolean closeAccountById(String account_number) {
        return this.accountRepository.CloseAccountById(account_number);
    }

    public Account getAccountByNumber(String account_number) {
        return this.accountRepository.getAccountByNumber(account_number);
    }

    public Boolean depositArgentByAccountId(String account_number, BigDecimal montant) {
        return this.accountRepository.addBalanceByIdAccount(account_number, montant);
    }

    public Boolean withdrawArgentByAccountId(String account_id, BigDecimal montant) {
        return this.accountRepository.subBalanceByIdAccount(account_id, montant);
    }

    public Boolean checkBalanceAccount(BigDecimal montant, String account_number) {
        BigDecimal balance = this.accountRepository.getBalanceByAccountNumber(account_number);

        if (montant.compareTo(balance) < 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkAccountTypeByNumber(String account_number)
    {
        return this.accountRepository.checkAccountTypeByNumber(account_number);
    }

    public Integer getIdClientByAccountId(String account_id)
    {
        return this.accountRepository.getIdClientByAccountId(account_id);
    }
}
