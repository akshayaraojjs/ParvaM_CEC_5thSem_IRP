package com.gaming.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class GameRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    private String category;

    @NotNull(message = "Cost per minute is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Cost per minute cannot be negative")
    private Double costPerMinute;

    public GameRequest() {}

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getCostPerMinute() { return costPerMinute; }
    public void setCostPerMinute(Double costPerMinute) { this.costPerMinute = costPerMinute; }
}