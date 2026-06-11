package com.enviro.assessment.junior.themba.service;

import com.enviro.assessment.junior.themba.dto.WithdrawalNoticeDTO;
import com.enviro.assessment.junior.themba.exception.ResourceNotFoundException;
import com.enviro.assessment.junior.themba.exception.WithdrawalValidationException;
import com.enviro.assessment.junior.themba.model.InvestmentProduct;
import com.enviro.assessment.junior.themba.model.Investor;
import com.enviro.assessment.junior.themba.model.WithdrawalNotice;
import com.enviro.assessment.junior.themba.repository.WithdrawalNoticeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WithdrawalService {

    private final WithdrawalNoticeRepository withdrawalRepository;
    private final InvestmentProductService productService;

    public WithdrawalService(WithdrawalNoticeRepository withdrawalRepository,
                              InvestmentProductService productService) {
        this.withdrawalRepository = withdrawalRepository;
        this.productService = productService;
    }

    public WithdrawalNoticeDTO createWithdrawal(WithdrawalNoticeDTO dto) {
        // Get the product
        InvestmentProduct product = productService.getProductEntityById(dto.getProductId());
        Investor investor = product.getInvestor();

        // Business Rule 1: Retirement withdrawals only if age > 65
        if (product.getProductType().equalsIgnoreCase("RETIREMENT")) {
            int age = Period.between(investor.getDateOfBirth(), LocalDate.now()).getYears();
            if (age <= 65) {
                throw new WithdrawalValidationException(
                    "Retirement withdrawals are only allowed for investors older than 65. Current age: " + age
                );
            }
        }

        // Business Rule 2: Withdrawal must not exceed balance
        if (dto.getAmount() > product.getBalance()) {
            throw new WithdrawalValidationException(
                "Withdrawal amount exceeds available balance of R" + product.getBalance()
            );
        }

        // Business Rule 3: Withdrawal must not exceed 90% of balance
        double maxAllowed = product.getBalance() * 0.90;
        if (dto.getAmount() > maxAllowed) {
            throw new WithdrawalValidationException(
                "Withdrawal amount exceeds 90% of balance. Maximum allowed: R" + String.format("%.2f", maxAllowed)
            );
        }

        // All rules passed - create the withdrawal
        WithdrawalNotice notice = new WithdrawalNotice();
        notice.setAmount(dto.getAmount());
        notice.setProduct(product);
        notice.setStatus("APPROVED");

        // Deduct balance
        product.setBalance(product.getBalance() - dto.getAmount());

        WithdrawalNotice saved = withdrawalRepository.save(notice);
        return toDTO(saved);
    }

    public List<WithdrawalNoticeDTO> getWithdrawalsByInvestor(Long investorId) {
        return withdrawalRepository.findByProductInvestorId(investorId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Convert Model to DTO
    private WithdrawalNoticeDTO toDTO(WithdrawalNotice notice) {
        WithdrawalNoticeDTO dto = new WithdrawalNoticeDTO();
        dto.setId(notice.getId());
        dto.setAmount(notice.getAmount());
        dto.setStatus(notice.getStatus());
        dto.setRejectionReason(notice.getRejectionReason());
        dto.setCreatedAt(notice.getCreatedAt());
        dto.setProductId(notice.getProduct().getId());
        return dto;
    }
}