package com.example.repository.implementation;

import com.example.config.DatabaseConnection;
import com.example.entity.Fee_rule;
import com.example.enums.ModeFeeRule;
import com.example.repository.Fee_ruleRepository;
import org.postgresql.util.PSQLException;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InMemoryFee_ruleRepository implements Fee_ruleRepository {

    public Connection connection;

    public InMemoryFee_ruleRepository()
    {
        this.connection = DatabaseConnection.getConnection();
    }


    public int getIdModeFeesByMode(ModeFeeRule modeFeeRule)
    {
        try
        {
            String sqlQuery = "SELECT id from modefeerules where mode_name = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,modeFeeRule.name());
          ResultSet rs =  preparedStatement.executeQuery();
          if(rs.next()) {
              return rs.getInt("id");
          }
          else
          {
              throw new SQLException("No mode found for: " + modeFeeRule.name());

          }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Fee_rule createFee_rule(Fee_rule feeRule)
    {
        try
        {
            String sqlQuery = "INSERT INTO fee_rules(operation_type,mode_id,value,currency,is_active,created_at) VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,feeRule.getOperation_type());
            preparedStatement.setInt(2,this.getIdModeFeesByMode(feeRule.getModeFeeRule()));
            preparedStatement.setBigDecimal(3,feeRule.getValue());
            preparedStatement.setString(4,feeRule.getCurrency());
            preparedStatement.setBoolean(5,feeRule.isIs_active());
            preparedStatement.setTimestamp(6,feeRule.getCreated_at());

            preparedStatement.executeUpdate();

            return feeRule;

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String DesactivateFeeRuleById(int id)
    {
        try {
            String sqlQuery = "UPDATE fee_rules set is_active = false where id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,id);
            int row = preparedStatement.executeUpdate();
            if(row > 0)
            {
                return "fee rule has been desactivated";
            }
            else
            {
                return "id not found";
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String ActivateRuleFee(int id) {
        try {
            String sqlQuery = "UPDATE fee_rules set is_active = true where id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,id);
          int row =  preparedStatement.executeUpdate();
          if(row > 0) {
              return "Fee rule is activated";
          }
          else {
              return "id rule not found";
          }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String updateModeFeeRule(int id,String modeFeeRule){
        try {
            String sqlQuery = "UPDATE fee_rules SET mode_fee = ? where id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,modeFeeRule);
            preparedStatement.setInt(2,id);
            int row = preparedStatement.executeUpdate();
            if(row > 0)
            {
                return "mode_fee updated with success";
            }
            else
            {
                return "id not found";
            }

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e.getMessage());
    }


    }
}
