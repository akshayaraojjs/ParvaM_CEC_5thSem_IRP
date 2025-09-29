package com.gaming.dto.response;

import java.time.LocalDateTime;

public class GameSessionResponse {
    private double cost;
    private double updatedBalance;
    private String gameName;
    private String memberName;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    // Getters & Setters
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public double getUpdatedBalance() { return updatedBalance; }
    public void setUpdatedBalance(double updatedBalance) { this.updatedBalance = updatedBalance; }

    public String getGameName() { return gameName; }
    public void setGameName(String gameName) { this.gameName = gameName; }

    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }

    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }

    public LocalDateTime getEndedAt() { return endedAt; }
    public void setEndedAt(LocalDateTime endedAt) { this.endedAt = endedAt; }
}