package com.example.dto;

import com.example.entity.Account;
import com.example.entity.Fee_rule;
import com.example.enums.TransactionType;
import com.example.enums.VirementStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionDTO {

    private UUID id;
    private BigDecimal amount;
    private AccountDTO transferIn;
    private AccountDTO transferOut;
    private TransactionType type;
    private VirementStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Fee_ruleDTO feeRule;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public AccountDTO getTransferIn() {
        return transferIn;
    }

    public void setTransferIn(AccountDTO transferIn) {
        this.transferIn = transferIn;
    }

    public AccountDTO getTransferOut() {
        return transferOut;
    }

    public void setTransferOut(AccountDTO transferOut) {
        this.transferOut = transferOut;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public VirementStatus getStatus() {
        return status;
    }

    public void setStatus(VirementStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Fee_ruleDTO getFeeRule() {
        return feeRule;
    }

    public void setFeeRule(Fee_ruleDTO feeRule) {
        this.feeRule = feeRule;
    }

    public TransactionDTO(BigDecimal amount, AccountDTO transferIn, AccountDTO transferOut, TransactionType type, VirementStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.transferIn = transferIn;
        this.transferOut = transferOut;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TransactionDTO(BigDecimal amount, AccountDTO transferIn, AccountDTO transferOut, TransactionType type, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.transferIn = transferIn;
        this.transferOut = transferOut;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TransactionDTO()
    {

    }

}
