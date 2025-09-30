package com.example.entity;

import com.example.enums.AccountType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class Account {

    private String id;
    private Client client;
    private Boolean isActive;
    private AccountType type;
    private BigDecimal solde;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    Random random = new Random();

    public Account(String id,Client client, AccountType type, Boolean isActive, BigDecimal solde, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.client = client;
        this.type = type;
        this.isActive = isActive;
        this.solde = solde;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Account()
    {

    }

    public Account(Client client, AccountType accountType, boolean isActive, BigDecimal solde, Timestamp createdAt, Timestamp updatedAt) {
        this.client = client;
        this.type = accountType;
        this.isActive = isActive;
        this.solde = solde;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Account(String id,Client client, AccountType accountType, boolean isActive, BigDecimal solde, Timestamp createdAt, Timestamp updatedAt) {
      this.id = id;
        this.client = client;
        this.type = accountType;
        this.isActive = isActive;
        this.solde = solde;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", client=" + client +
                ", isActive=" + isActive +
                ", type=" + type +
                ", solde=" + solde +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", random=" + random +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
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
