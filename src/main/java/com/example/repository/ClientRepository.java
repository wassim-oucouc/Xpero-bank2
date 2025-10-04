package com.example.repository;

import com.example.entity.Client;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ClientRepository {

    public Client createClient(Client client);
    public Client getClientById(int id);
    public List<Client> getAllClients();
    public Client getClientByCin(String cin);
    public String closeClientByCin(String cin);
    public BigDecimal getSallaireClientById(int id);
}
