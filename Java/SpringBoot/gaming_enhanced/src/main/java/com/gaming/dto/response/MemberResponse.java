package com.gaming.dto.response;

public class MemberResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String bio;
    private Double balance;

    public MemberResponse() {}

    public MemberResponse(Long id, String name, String email, String phone, String bio, Double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bio = bio;
        this.balance = balance;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
}