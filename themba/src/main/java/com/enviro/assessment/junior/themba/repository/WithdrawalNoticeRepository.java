package com.enviro.assessment.junior.themba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enviro.assessment.junior.themba.model.WithdrawalNotice;

import java.util.List;

@Repository
public interface WithdrawalNoticeRepository extends JpaRepository<WithdrawalNotice, Long> {
    List<WithdrawalNotice> findByProductId(Long productId);
    List<WithdrawalNotice> findByProductInvestorId(Long investorId);
}
