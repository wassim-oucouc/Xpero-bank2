package com.example.repository.implementation;

import com.example.config.DatabaseConnection;
import com.example.entity.Transaction;
import com.example.enums.TransactionType;
import com.example.repository.TransactionRepository;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InMemoryTransactionRepository implements TransactionRepository {

    public Connection connection;

    public InMemoryTransactionRepository()
    {
        this.connection = DatabaseConnection.getConnection();
    }

    public Integer getIdByTypeString(String type_name)
    {
        try
        {
            String sqlQuery = "SELECT id FROM transactiontype WHERE type_name = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,type_name);
         ResultSet resultSet =  preparedStatement.executeQuery();
         if(resultSet.next())
         {
             return resultSet.getInt("id");
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
    public Transaction createTransaction(Transaction transaction)
    {

        try
        {
            String sqlQuery = "INSERT INTO transactions(id,amount,transfer_in_account_id,transfer_out_account_id,type_id,created_at,updated_at) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,transaction.getId().toString());
            preparedStatement.setBigDecimal(2,transaction.getAmount());
            preparedStatement.setString(3,transaction.getTransferIn().getId());
            preparedStatement.setString(4,transaction.getTransferOut().getId());
            preparedStatement.setInt(5,this.getIdByTypeString(transaction.getType().name()));
         int row =    preparedStatement.executeUpdate();
         if(row > 0)
         {
             return transaction;
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
