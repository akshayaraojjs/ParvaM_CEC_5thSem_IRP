package com.gaming.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_sessions")
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(nullable = false)
    private int minutesPlayed;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false, updatable = false)
    private LocalDateTime startedAt = LocalDateTime.now();

    @Column
    private LocalDateTime endedAt;

    public GameSession() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }

    public int getMinutesPlayed() { return minutesPlayed; }
    public void setMinutesPlayed(int minutesPlayed) { this.minutesPlayed = minutesPlayed; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }

    public LocalDateTime getEndedAt() { return endedAt; }
    public void setEndedAt(LocalDateTime endedAt) { this.endedAt = endedAt; }
}