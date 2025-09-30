package com.example.entity;

import com.example.enums.TransactionType;
import com.example.enums.VirementStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class Transaction {

    private UUID id;
    private BigDecimal amount;
    private Account transferIn;
    private Account transferOut;
    private TransactionType type;
    private VirementStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Fee_rule feeRule;

    public Transaction(UUID id, BigDecimal amount, Account transferIn, Account transferOut, TransactionType type, VirementStatus status, LocalDateTime createdAt, LocalDateTime updatedAt, Fee_rule feeRule) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.transferIn = transferIn;
        this.transferOut = transferOut;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.feeRule = feeRule;
    }

    public Transaction()
    {

    }

    public Account getTransferIn() {
        return transferIn;
    }

    public void setTransferIn(Account transferIn) {
        this.transferIn = transferIn;
    }

    public Account getTransferOut() {
        return transferOut;
    }

    public void setTransferOut(Account transferOut) {
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

    public Fee_rule getFeeRule() {
        return feeRule;
    }

    public void setFeeRule(Fee_rule feeRule) {
        this.feeRule = feeRule;
    }

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
}
