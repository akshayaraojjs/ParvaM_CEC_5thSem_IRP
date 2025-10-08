package com.gaming.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recharges")
public class Recharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private double updatedBalance;

    @Column(nullable = false)
    private LocalDateTime rechargeAt;

    public Recharge() {}

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getUpdatedBalance() { return updatedBalance; }
    public void setUpdatedBalance(double updatedBalance) { this.updatedBalance = updatedBalance; }

    public LocalDateTime getRechargeAt() { return rechargeAt; }
    public void setRechargeAt(LocalDateTime rechargeAt) { this.rechargeAt = rechargeAt; }
}