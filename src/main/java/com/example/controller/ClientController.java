package com.example.controller;

import com.example.dto.ClientDTO;
import com.example.entity.Client;
import com.example.mapper.ClientMapper;
import com.example.service.ClientService;

import java.util.List;
import java.util.Optional;

public class ClientController {

    public ClientService clientService;
    public ClientMapper clientMapper;

    public ClientController(ClientService clientService,ClientMapper clientMapper)
    {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }


    public Client addClient(ClientDTO clientDTO)
    {
        Client client = this.clientMapper.ToEntity(clientDTO);
        return this.clientService.createClient(client);
    }


    public List<ClientDTO> getAllClients()
    {
        List<Client> clients = this.clientService.getAllClients();
      return  clients.stream().map(ClientMapper::ToDTO).toList();
    }

    public String closeClient(String cin)
    {
        return this.clientService.closeClientByCin(cin);
    }

}
