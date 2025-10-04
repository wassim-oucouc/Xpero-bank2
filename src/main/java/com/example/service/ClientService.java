package com.example.service;

import com.example.entity.Client;
import com.example.repository.ClientRepository;

import java.math.BigDecimal;
import java.util.List;

public class ClientService {

    public ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository)
    {
        this.clientRepository = clientRepository;
    }

    public Client createClient(Client client)
    {
        return this.clientRepository.createClient(client);
    }

    public List<Client> getAllClients()
    {
        return this.clientRepository.getAllClients();
    }

    public Client getClientByCin(String cin)
    {
        return this.clientRepository.getClientByCin(cin);
    }

    public String closeClientByCin(String cin)
    {
        return this.clientRepository.closeClientByCin(cin);
    }

    public Client getClientById(int id)
    {
        return this.clientRepository.getClientById(id);
    }

    public BigDecimal getSalaireById(int id)
    {
        return this.clientRepository.getSallaireClientById(id);
    }


}
