package com.example.entity;

import com.example.enums.Role;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class User {

    protected UUID id;
    protected String name;
    protected String lastname;
    protected String email;
    protected String address;
    protected Role role;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;

    public User(String name, String lastname, String email, String password, Role role, String address, Timestamp createdAt, Timestamp updatedAt) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.address = address;
        this.createdAt = createdAt;
        this.password = password;
        this.updatedAt = updatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
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

    protected String password;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
