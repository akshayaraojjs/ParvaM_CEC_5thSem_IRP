package com.gaming.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RechargeRequest {

    @NotNull(message = "Member ID is required")
    private Long memberId;

    @Min(value = 1, message = "Amount must be greater than 0")
    private double amount;

    // --- Getters & Setters ---
    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}