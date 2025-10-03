package com.example.dto;

import com.example.entity.Account;
import com.example.entity.Fee_rule;
import com.example.enums.CreditStatus;
import com.example.enums.CreditType;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CreditDTO {

    public Integer id;
    public BigDecimal amount;
    public Integer duree;
    public Float Taux;
    public String justification;
    public Boolean is_active;
    public CreditType creditType;
    public AccountDTO account;
    public CreditStatus creditStatus;
    public Timestamp created_at;
    public Timestamp updated_at;

    public Float getTaux() {
        return Taux;
    }

    public void setTaux(Float taux) {
        Taux = taux;
    }

    public CreditDTO(Integer id, BigDecimal amount, Integer duree, Fee_rule feeRule, Boolean is_active, String justification, CreditType creditType, AccountDTO account, CreditStatus creditStatus, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.amount = amount;
        this.duree = duree;
        this.feeRule = feeRule;
        this.is_active = is_active;
        this.justification = justification;
        this.creditType = creditType;
        this.account = account;
        this.creditStatus = creditStatus;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public CreditDTO()
    {

    }

    public Fee_rule feeRule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Fee_rule getFeeRule() {
        return feeRule;
    }

    public void setFeeRule(Fee_rule feeRule) {
        this.feeRule = feeRule;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public CreditType getCreditType() {
        return creditType;
    }

    public void setCreditType(CreditType creditType) {
        this.creditType = creditType;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public CreditStatus getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(CreditStatus creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
