package com.example.repository.implementation;

import com.example.config.DatabaseConnection;
import com.example.entity.Client;
import com.example.repository.ClientRepository;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryClientRepository implements ClientRepository {

    public Connection connection;

    public InMemoryClientRepository() {
        this.connection = DatabaseConnection.getConnection();
    }


    public Client createClient(Client client) {
        try {
            String sqlQuery = "INSERT INTO Clients(firstname,lastname,cin,email,address,salaire) VALUES (?, ?,?, ?, ?, ?)";
            PreparedStatement prepare = this.connection.prepareStatement(sqlQuery);
            prepare.setString(1, client.getName());
            prepare.setString(2, client.getLastname());
            prepare.setString(3, client.getCin());
            prepare.setString(4, client.getEmail());
            prepare.setString(5, client.getAddress());
            prepare.setBigDecimal(6, client.getSallaire());


            int row = prepare.executeUpdate();
            if (row > 0) {
                return client;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Client updateClientById(UUID id, Client client) {
        try {
            String sqlQuery = "update clients  set firstname = ?,lastname = ?,email = ? ,cin = ?,address = ?,salaire = ? where id = ?";
            PreparedStatement prepare = this.connection.prepareStatement(sqlQuery);
            prepare.setString(1, client.getName());
            prepare.setString(2, client.getLastname());
            prepare.setString(3, client.getEmail());
            prepare.setString(4, client.getCin());
            prepare.setString(5, client.getAddress());
            prepare.setBigDecimal(6, client.getSallaire());
            int row = prepare.executeUpdate();
            if (row > 0) {
                return client;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteClientById(UUID id) {
        try {
            String sqlQuery = "DELETE FROM clients where id = ?";
            PreparedStatement prepare = this.connection.prepareStatement(sqlQuery);
            prepare.executeQuery().close();
            return "user deleted with success";
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public String updatePasswordById(UUID id, String password) {
        String querySql = "update Clients set password = ? where id = ?";
        try {
            PreparedStatement prepare = this.connection.prepareStatement(querySql);
            prepare.setString(1, password);
            prepare.setObject(2, id);
            prepare.executeQuery().close();
            return "password update with success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String updateEmailById(UUID id, String email) {
        String querySql = "update Clients set email = ? where id = ?";
        try {
            PreparedStatement prepare = this.connection.prepareStatement(querySql);
            prepare.setString(1, email);
            prepare.setObject(2, id);
            prepare.executeQuery().close();
            return "client Updated With Success";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Client getClientByCin(String cin)
    {
        try
        {
            String sqlQuery = "SELECT * FROM clients where cin = ?";
            PreparedStatement prepare = this.connection.prepareStatement(sqlQuery);
            prepare.setString(1,cin);
           ResultSet resultSet =  prepare.executeQuery();

            if(resultSet.next())
            {
                Client client = new Client(resultSet.getInt("id"),resultSet.getString("firstname"),resultSet.getString("lastname"),resultSet.getString("email"),resultSet.getString("cin"),resultSet.getString("address"),resultSet.getBigDecimal("salaire"));
                return client;
            }
            else {
                return null;
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Client getClientById(int id) {
        try {
            String sqlQuery = "SELECT * FROM clients where id = ?";
            PreparedStatement prepare = this.connection.prepareStatement(sqlQuery);
            prepare.setObject(1, id);
            ResultSet result = prepare.executeQuery();
            Client client = null;
            if (result.next()) {
                client = new Client(
                        id,
                        result.getString("firstname"),
                        result.getString("lastname"),
                        result.getString("email"),
                        result.getString("address"),
                        result.getString("cin"),
                        result.getBigDecimal("salaire")
                );
            }
            return client;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
}


public List<Client> getAllClients()
{
    List<Client> clients = new ArrayList<>();
    try
    {
        String sqlQuery = "SELECT * FROM clients";
        PreparedStatement prepare = this.connection.prepareStatement(sqlQuery);
        ResultSet result =   prepare.executeQuery();
        while (result.next())
        {
            Client client = new Client(result.getInt("id"),result.getString("firstname"),result.getString("lastname"),result.getString("email"),result.getString("cin"),result.getString("address"),result.getBigDecimal("salaire"));
            clients.add(client);
        }

        return clients;



    }
    catch (SQLException e)
    {
        throw  new RuntimeException(e.getMessage());
    }
}

    public String closeClientByCin(String cin)
    {
        try {
            String sqlQuery = "UPDATE clients set is_active = false where cin = ?";
            PreparedStatement prepare = this.connection.prepareStatement(sqlQuery);
            prepare.setString(1,cin);
           int row =  prepare.executeUpdate();
           if(row > 0)
           {
               return "client closed";
           }
           else
           {
               return null;
           }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
