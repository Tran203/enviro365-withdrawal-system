package com.enviro.assessment.junior.themba.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Withdrawal_Notices")
public class WithdrawalNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private Double amount;

    private String status; // PENDING, APPROVED, REJECTED

    private String rejectionReason;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private InvestmentProduct product;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = "PENDING";
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String reason) { this.rejectionReason = reason; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public InvestmentProduct getProduct() { return product; }
    public void setProduct(InvestmentProduct product) { this.product = product; }
}
