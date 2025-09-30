package com.example.repository;

import com.example.entity.Account;
import com.example.enums.AccountType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountRepository {

    public Account createAccount(Account account);
    public Boolean checkAccountTypeClient(int type_id,String cin);
    public String getIdAccountTypeByString(int type_id);
    public String deleteAccountById(UUID id);
    public String updateAccountTypeById(String id, AccountType accountType);
    public Boolean CloseAccountById(String id);
    public List<Account> getAllAccounts();
    public Boolean checkAccountExists(String account_number);
    public Boolean addBalanceByIdAccount(String account_id, BigDecimal solde);
    public Account getAccountByNumber(String number);
}
