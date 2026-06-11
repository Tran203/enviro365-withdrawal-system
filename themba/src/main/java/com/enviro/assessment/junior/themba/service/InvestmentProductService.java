package com.enviro.assessment.junior.themba.service;

import com.enviro.assessment.junior.themba.dto.InvestmentProductDTO;
import com.enviro.assessment.junior.themba.exception.ResourceNotFoundException;
import com.enviro.assessment.junior.themba.model.InvestmentProduct;
import com.enviro.assessment.junior.themba.model.Investor;
import com.enviro.assessment.junior.themba.repository.InvestmentProductRepository;
import com.enviro.assessment.junior.themba.repository.InvestorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestmentProductService {

    private final InvestmentProductRepository productRepository;
    private final InvestorRepository investorRepository;

    public InvestmentProductService(InvestmentProductRepository productRepository,
                                     InvestorRepository investorRepository) {
        this.productRepository = productRepository;
        this.investorRepository = investorRepository;
    }

    public List<InvestmentProductDTO> getProductsByInvestor(Long investorId) {
        return productRepository.findByInvestorId(investorId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public InvestmentProductDTO getProductById(Long id) {
        InvestmentProduct product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return toDTO(product);
    }

    // Convert Model to DTO
    public InvestmentProductDTO toDTO(InvestmentProduct product) {
        InvestmentProductDTO dto = new InvestmentProductDTO();
        dto.setId(product.getId());
        dto.setProductName(product.getProductName());
        dto.setProductType(product.getProductType());
        dto.setBalance(product.getBalance());
        dto.setInvestorId(product.getInvestor().getId());
        return dto;
    }

    public InvestmentProduct getProductEntityById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }
}