package com.example.repository.implementation;

import com.example.config.DatabaseConnection;
import com.example.entity.Credit;
import com.example.repository.CreditRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InMemoryCreditRepository implements CreditRepository {


    public Connection connection;


    public InMemoryCreditRepository()
    {
        this.connection = DatabaseConnection.getConnection();
    }

//    public Credit createCredit(Credit credit)
//    {
//        try
//        {
//            String sqlQuery = "INSERT INTO credits(id,) values()"
//        }
//        catch (SQLException e)
//        {
//            throw new RuntimeException(e.getMessage());
//        }
//    }


    public Credit createCredit(Credit credit)
    {
        try
        {
            String sqlQuery = "INSERT INTO credits(amount,duree,taux,fee_rule_id,justification,credit_type_id,account_id,status_id,created_at,updated_at) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setBigDecimal(1,credit.amount);
            preparedStatement.setInt(2,credit.getDuree());
            preparedStatement.setFloat(3,credit.getTaux());
            preparedStatement.setInt(4,credit.getFeeRule().getId());
            preparedStatement.setString(5,credit.getJustification());
            if(credit.getCreditType().name().equals("SIMPLE"))
            {
                preparedStatement.setInt(6,1);
            }
            else
            {
                preparedStatement.setInt(6,2);
            }
            preparedStatement.setString(7,credit.getAccount().getId());
            preparedStatement.setTimestamp(8,credit.getCreated_at());
            preparedStatement.setTimestamp(9,credit.getUpdated_at());
            int row = preparedStatement.executeUpdate();

            if(row > 0)
            {
                return credit;
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

    public Boolean checkCreditLate(String account_id)
    {
        try {
            String sqlQuery = "SELECT * FROM credits WHERE account_id = ? AND status_id = 2";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, account_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
}
