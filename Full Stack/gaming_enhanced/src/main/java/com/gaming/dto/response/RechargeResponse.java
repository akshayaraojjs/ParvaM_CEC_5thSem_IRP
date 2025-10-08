package com.gaming.dto.response;

import java.time.LocalDateTime;

public class RechargeResponse {

    private Long id;
    private Long memberId;
    private String memberName;
    private double amount;
    private double updatedBalance;
    private LocalDateTime rechargeAt;

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getUpdatedBalance() { return updatedBalance; }
    public void setUpdatedBalance(double updatedBalance) { this.updatedBalance = updatedBalance; }

    public LocalDateTime getRechargeAt() { return rechargeAt; }
    public void setRechargeAt(LocalDateTime rechargeAt) { this.rechargeAt = rechargeAt; }
}