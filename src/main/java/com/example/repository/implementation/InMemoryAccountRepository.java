package com.example.repository.implementation;

import com.example.config.DatabaseConnection;
import com.example.entity.Account;
import com.example.entity.Client;
import com.example.enums.AccountType;
import com.example.repository.AccountRepository;
import com.example.repository.ClientRepository;
import com.example.service.ClientService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryAccountRepository implements AccountRepository {

    public Connection connection;
    private ClientRepository clientRepository;
    private ClientService clientService;

    public InMemoryAccountRepository(ClientRepository clientRepository, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
        this.connection = DatabaseConnection.getConnection();
    }

    public Account createAccount(Account account) {

        try {
            String sqlQuery = "INSERT INTO accounts(id,client_id,is_active,solde,created_at,type_id)values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, account.getId());
            preparedStatement.setInt(2, account.getClient().getId());
            preparedStatement.setBoolean(3, account.getActive());
            preparedStatement.setBigDecimal(4, account.getSolde());
            preparedStatement.setTimestamp(5, account.getCreatedAt());
            if (account.getType().name().equals("COURANT")) {
                preparedStatement.setInt(6, 1);
            } else if (account.getType().name().equals("EPARGNE")) {
                preparedStatement.setInt(6, 2);
            } else if (account.getType().name().equals("CREDIT")) {
                preparedStatement.setInt(6, 3);
            }
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                return account;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String deleteAccountById(UUID id) {
        try {
            String sqlQuery = "DELETE FROM accounts where id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setObject(1, id);
            preparedStatement.executeQuery();
            return "account deleted with success";
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public String updateAccountTypeById(String id, AccountType accountType) {
        try {
            String sqlQuery = "UPDATE accounts SET account_type = ? where id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setObject(1, accountType);
            preparedStatement.setString(2, id);
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                return "type of account is updated";
            } else {
                return "type account is not updated";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Boolean CloseAccountById(String id) {
        try {
            String sqlQuery = "UPDATE accounts set is_active = FALSE where id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, id);
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try {
            String sqlQuery = "SELECT accounts.id,accounts.type_id,accounts.is_active,accounts.solde,accounts.created_at,accounts.updated_at,accounts.client_id,clients.firstname,clients.lastname,clients.cin FROM accounts inner join clients on clients.id = accounts.client_id";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int clientId = result.getInt("client_id");
                Client client = this.clientRepository.getClientById(clientId);
                String type_account = this.getIdAccountTypeByString(result.getInt("type_id"));
                AccountType accountType = AccountType.valueOf(type_account.toUpperCase());
                Account account = new Account(client, accountType, result.getBoolean("is_active"), result.getBigDecimal("solde"), result.getTimestamp("created_at"), result.getTimestamp("updated_at"));

                accounts.add(account);

            }

            return accounts;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getIdAccountTypeByString(int type_id) {
        try {
            String sqlQuery = "SELECT * FROM accounttypes WHERE id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, type_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("type_name");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public Boolean checkAccountTypeClient(int type_id, String cin) {
        Client client = this.clientRepository.getClientByCin(cin);

        try {
            String sqlQuery = "SELECT * FROM accounts where type_id = ? AND client_id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, type_id);
            preparedStatement.setInt(2, client.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            } else {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Boolean checkAccountExists(String account_number) {
        try {
            String sqlQuery = "SELECT * FROM accounts WHERE id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, account_number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Boolean addBalanceByIdAccount(String account_id, BigDecimal solde) {
        try {
            String sqlQuery = "UPDATE accounts set solde = solde + ? where id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setBigDecimal(1,solde);
            preparedStatement.setString(2,account_id);
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Account getAccountByNumber(String number)
    {
        try
        {
            String sqlQuery = "SELECT * FROM accounts WHERE id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                Client client = this.clientRepository.getClientById(resultSet.getInt("type_id"));
                Account account = new Account();
                account.setId(resultSet.getString("id"));
                account.setType(AccountType.valueOf(this.getIdAccountTypeByString(resultSet.getInt("type_id"))));
                account.setSolde(resultSet.getBigDecimal("solde"));
                account.setActive(resultSet.getBoolean("is_active"));
                account.setClient(this.clientService.getClientById(resultSet.getInt("client_id")));
                account.setCreatedAt(resultSet.getTimestamp("created_at"));
                account.setUpdatedAt(resultSet.getTimestamp("updated_at"));
                return account;
            }
            else
            {
                return null;
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

}