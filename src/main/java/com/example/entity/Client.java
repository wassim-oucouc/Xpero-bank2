package com.example.entity;

import java.math.BigDecimal;

public class Client{

    private int id;
    private String name;
    private String lastname;
    private String email;
    private String cin;
    private String address;
    private BigDecimal sallaire;

    public Client(int id,String name, String lastname, String email, String address, String cin, BigDecimal sallaire)
    {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.sallaire = sallaire;
        this.cin = cin;
    }

    public Client()
    {

    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public BigDecimal getSallaire() {
        return sallaire;
    }

    public void setSallaire(BigDecimal sallaire) {
        this.sallaire = sallaire;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", cin='" + cin + '\'' +
                ", address='" + address + '\'' +
                ", sallaire=" + sallaire +
                '}';
    }
}
