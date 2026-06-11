package com.enviro.assessment.junior.themba.controller;

import com.enviro.assessment.junior.themba.dto.InvestmentProductDTO;
import com.enviro.assessment.junior.themba.service.InvestmentProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class InvestmentProductController {

    private final InvestmentProductService productService;

    public InvestmentProductController(InvestmentProductService productService) {
        this.productService = productService;
    }

    // GET all products for an investor
    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<InvestmentProductDTO>> getProductsByInvestor(@PathVariable Long investorId) {
        return ResponseEntity.ok(productService.getProductsByInvestor(investorId));
    }

    // GET single product
    @GetMapping("/{id}")
    public ResponseEntity<InvestmentProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}