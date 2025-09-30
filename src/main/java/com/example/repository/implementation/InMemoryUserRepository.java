package com.example.repository.implementation;

import com.example.config.DatabaseConnection;
import com.example.entity.User;
import com.example.enums.Role;
import com.example.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class InMemoryUserRepository implements UserRepository{

    public Connection connection;

    public InMemoryUserRepository()
    {
        this.connection = DatabaseConnection.getConnection();
    }


    public User CheckEmailAndPassword(String email, String password)
    {

        try {
            String sqlQuery = "SELECT firstname,lastname,email,password,address,role,created_at,updated_at from users where email = ? AND password = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet =   preparedStatement.executeQuery();
            if(!resultSet.next())
            {
                return null;
            }
            else
            {
                System.out.println(resultSet.getString("password"));

                return new User(resultSet.getString("firstname"),resultSet.getString("lastname"),resultSet.getString("email"),resultSet.getString("password"), Role.valueOf(resultSet.getString("role")), resultSet.getString("address"),resultSet.getTimestamp("created_at"),resultSet.getTimestamp("updated_at"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String DeleteUserById(UUID id) {
        try {
            String sqlQuery = "DELETE FROM users where id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setObject(1, id);
            preparedStatement.executeQuery().close();
            return "User is Deleted";
        }catch (SQLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String UpdateEmailById(UUID id,String email)
    {
        try {
            String sqlQuery = "UPDATE users set email = ? where id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setObject(2,id);
         int row =    preparedStatement.executeUpdate();
         if(row > 0) {
             return "email updated with success";
         }
         else
         {
             return "no user found";
         }
        }catch (SQLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    public User createUser(User user)
    {
        try
        {
            String sqlQuery = "INSERT INTO users(firstname,lastname,email,password,address,role,created_at) values(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getLastname());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getAddress());
            preparedStatement.setObject(6,user.getRole());
            preparedStatement.setTimestamp(7,user.getCreatedAt());
            preparedStatement.executeQuery();
            return user;

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String updatePasswordUserById(UUID id,String password)
    {
        try
        {
            String sqlQuery = "UPDATE users set password = ? where id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,password);
            preparedStatement.setObject(2,id);
            int row = preparedStatement.executeUpdate();
            if(row > 0)
            {
                return "User Password updated with success";
            }
            else
            {
                return "user id not found";
            }

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String updateProfileUserById(UUID id,User user)
    {
        try
        {
           String sqlQuery = "UPDATE users set email = ?,address = ? where id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getAddress());
            preparedStatement.setObject(3,user.getId());
            int row = preparedStatement.executeUpdate();
            if(row > 0)
            {
                return "profile updated with success";
            }
            else
            {
                return "user id not found";
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }


}

