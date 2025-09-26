// Where is the file? (Path of the current file)
package com.gaming.model;

// header file to make use of Annotations
// @ - Annotations
import jakarta.persistence.*;

// Entity refers to Table
@Entity
@Table(name = "games")
// Table names will be plural
// Class/Method names will be Singular
public class Game {
    // Primary Key - id
    @Id
    // Generate the Primary Key value automatically and also auto_increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Type of data for id - Long (Large Integer Value)
    private Long id;

    // Type of data for name  - String (varchar) 
    private String name;
    private String description;
    private String category;
    // Type of data for costPerMinute - double (Large Float Value)
    private double costPerMinute;

    // Getters & Setters to store or access the private data
    public Long getId() {
        return id;
    }

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

    public double getCostPerMinute() {
        return costPerMinute;
    }

    public void setCostPerMinute(double costPerMinute) {
        this.costPerMinute = costPerMinute;
    }
}