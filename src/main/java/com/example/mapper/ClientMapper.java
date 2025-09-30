package com.example.mapper;

import com.example.dto.ClientDTO;
import com.example.entity.Client;

public class ClientMapper {



    public Client ToEntity(ClientDTO clientDTO)
    {

        if(clientDTO == null)
        {
            return null;
        }
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        client.setEmail(clientDTO.getEmail());
        client.setAddress(clientDTO.getAddress());
        client.setSallaire(clientDTO.getSallaire());
        client.setCin(clientDTO.getCin());


        return client;
    }
    public static ClientDTO ToDTO(Client client)
    {
        if(client == null)
        {
            return null;
        }
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setLastname(client.getLastname());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setSallaire(client.getSallaire());
        clientDTO.setCin(client.getCin());

        return clientDTO;
    }
}
