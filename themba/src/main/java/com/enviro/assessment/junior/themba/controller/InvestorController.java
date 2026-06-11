package com.enviro.assessment.junior.themba.controller;

import com.enviro.assessment.junior.themba.dto.InvestorDTO;
import com.enviro.assessment.junior.themba.service.InvestorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investors")
@CrossOrigin(origins = "*")
public class InvestorController {

    private final InvestorService investorService;

    public InvestorController(InvestorService investorService) {
        this.investorService = investorService;
    }

    // GET all investors
    @GetMapping
    public ResponseEntity<List<InvestorDTO>> getAllInvestors() {
        return ResponseEntity.ok(investorService.getAllInvestors());
    }

    // GET single investor by ID
    @GetMapping("/{id}")
    public ResponseEntity<InvestorDTO> getInvestorById(@PathVariable Long id) {
        return ResponseEntity.ok(investorService.getInvestorById(id));
    }
}