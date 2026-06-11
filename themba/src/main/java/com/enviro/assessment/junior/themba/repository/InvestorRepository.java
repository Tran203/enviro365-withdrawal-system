package com.enviro.assessment.junior.themba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enviro.assessment.junior.themba.model.Investor;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long>{
    
}
