package com.example.dto;

import com.example.entity.Client;
import com.example.enums.AccountType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Random;

public class AccountDTO {

    private String id;
    private ClientDTO client;
    private Boolean isActive;
    private AccountType type;
    private BigDecimal solde;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    Random random = new Random();


    public AccountDTO(String id,ClientDTO client, AccountType type, Boolean isActive, BigDecimal solde, Timestamp createdAt, Timestamp updatedAt) {
        this.id = "BK-"+ random.nextInt(1000) +"-"+random.nextInt(1000);;
        this.client = client;
        this.type = type;
        this.isActive = isActive;
        this.solde = solde;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AccountDTO()
    {

    }


    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id='" + id + '\'' +
                ", client=" + client +
                ", isActive=" + isActive +
                ", type=" + type +
                ", solde=" + solde +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
