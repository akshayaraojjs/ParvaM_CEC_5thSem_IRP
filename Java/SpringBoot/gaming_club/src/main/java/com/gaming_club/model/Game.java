package com.gaming_club.model;

import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;
    
    @Column(nullable = false)
    private String category;

    @Column(name = "cost_per_minute", nullable = false)
    private double costPerMinute;

    // Default constructor (required by JPA)
    public Game() {}

    // Parameterized constructor
    public Game(String name, String description, String category, double costPerMinute) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.costPerMinute = costPerMinute;
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getCostPerMinute() {
        return costPerMinute;
    }

    public void setCostPerMinute(double costPerMinute) {
        this.costPerMinute = costPerMinute;
    }
}