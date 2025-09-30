package com.example.entity;

import com.example.enums.ModeFeeRule;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Fee_rule {
    private int id;
    private String operation_type;
    private ModeFeeRule modeFeeRule;
    private BigDecimal value;
    private String currency;
    private boolean is_active;
    private Timestamp created_at;

    public int getId() {
        return id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Fee_rule(String operation_type, int id, ModeFeeRule modeFeeRule, BigDecimal value, String currency, boolean is_active, Timestamp createdAt) {
        this.operation_type = operation_type;
        this.id = id;
        this.modeFeeRule = modeFeeRule;
        this.value = value;
        this.currency = currency;
        this.is_active = is_active;
        created_at = createdAt;
    }

    public Fee_rule()
    {

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }

    public ModeFeeRule getModeFeeRule() {
        return modeFeeRule;
    }

    public void setModeFeeRule(ModeFeeRule modeFeeRule) {
        this.modeFeeRule = modeFeeRule;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}
