package com.gaming_app.model;

// Header File for using annotations and validations
import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Game {
    // Primary Key - id
    @Id 
    // Primary Key - Auto_increment
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    // Validation for checking the name and its uniqueness
    // Cannot leave name as empty (nullable = false)
    // Name cannot be repeated or cannot be duplicated (unique = true)
    @Column(nullable = false, unique = true)
    private String name;

    // Description can be longer so given it as Text
    @Column(columnDefinition  = "TEXT")
    private String description;

    // Cannot give category as empty (nullable = false)
    @Column(nullable = false)
    private String category;

    // Cannot give cost per minute info as empty (nullable = false)
    @Column(name = "cost_per_minute", nullable = false)
    private double costPerMinute;

    // Default Constructor 
    public Game() {}

    // Getters
    public Long getId() {
        return id;
    }

    // Setters
    public void setId(Long id) {
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

    public Double getCostPerMinute() {
        return costPerMinute;
    }

    public void setCostPerMinute(Double costPerMinute) {
        this.costPerMinute = costPerMinute;
    }
}

// 5 Types of Naming Case:
// AKSHAY RAO - Upper case
// akshay rao - Lower case
// akshay_rao - Snake Case
// akshayRao - Camel Case
// AkshayRao - Pascal Case