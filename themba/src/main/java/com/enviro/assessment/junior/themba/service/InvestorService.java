package com.enviro.assessment.junior.themba.service;

import com.enviro.assessment.junior.themba.dto.InvestorDTO;
import com.enviro.assessment.junior.themba.exception.ResourceNotFoundException;
import com.enviro.assessment.junior.themba.model.Investor;
import com.enviro.assessment.junior.themba.repository.InvestorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestorService {

    private final InvestorRepository investorRepository;

    public InvestorService(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public List<InvestorDTO> getAllInvestors() {
        return investorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public InvestorDTO getInvestorById(Long id) {
        Investor investor = investorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investor not found with id: " + id));
        return toDTO(investor);
    }

    // Convert Model to DTO
    private InvestorDTO toDTO(Investor investor) {
        InvestorDTO dto = new InvestorDTO();
        dto.setId(investor.getId());
        dto.setName(investor.getName());
        dto.setEmail(investor.getEmail());
        dto.setDateOfBirth(investor.getDateOfBirth());
        return dto;
    }
}