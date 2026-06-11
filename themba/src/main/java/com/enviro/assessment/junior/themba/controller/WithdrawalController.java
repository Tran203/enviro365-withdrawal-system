package com.enviro.assessment.junior.themba.controller;

import com.enviro.assessment.junior.themba.dto.WithdrawalNoticeDTO;
import com.enviro.assessment.junior.themba.service.WithdrawalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/withdrawals")
@CrossOrigin(origins = "*")
public class WithdrawalController {

    private final WithdrawalService withdrawalService;

    public WithdrawalController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    // POST create withdrawal
    @PostMapping
    public ResponseEntity<WithdrawalNoticeDTO> createWithdrawal(@Valid @RequestBody WithdrawalNoticeDTO dto) {
        return ResponseEntity.ok(withdrawalService.createWithdrawal(dto));
    }

    // GET withdrawal history for investor
    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<WithdrawalNoticeDTO>> getWithdrawalsByInvestor(@PathVariable Long investorId) {
        return ResponseEntity.ok(withdrawalService.getWithdrawalsByInvestor(investorId));
    }

    // GET export CSV
    @GetMapping("/export/{investorId}")
    public ResponseEntity<byte[]> exportCSV(@PathVariable Long investorId) {
        List<WithdrawalNoticeDTO> withdrawals = withdrawalService.getWithdrawalsByInvestor(investorId);

        StringBuilder csv = new StringBuilder();
        csv.append("ID,Amount,Status,Rejection Reason,Date,Product ID\n");

        for (WithdrawalNoticeDTO w : withdrawals) {
            csv.append(w.getId()).append(",")
               .append(w.getAmount()).append(",")
               .append(w.getStatus()).append(",")
               .append(w.getRejectionReason() != null ? w.getRejectionReason() : "").append(",")
               .append(w.getCreatedAt()).append(",")
               .append(w.getProductId()).append("\n");
        }

        byte[] csvBytes = csv.toString().getBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDispositionFormData("attachment", "withdrawals.csv");

        return ResponseEntity.ok()
                .headers(headers)
                .body(csvBytes);
    }
}