package com.enviro.assessment.junior.themba.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Investment_Products")
public class InvestmentProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Product type is required")
    private String productType; //RETIREMENT, SAVINGS

    @NotNull(message = "Balance is required")
    @DecimalMin(value = "0.0", message = "Balance cannot be negative")
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "investor_id", nullable = false)
    private Investor investor;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }

    public Investor getInvestor() { return investor; }
    public void setInvestor(Investor investor) { this.investor = investor; }
    
}
