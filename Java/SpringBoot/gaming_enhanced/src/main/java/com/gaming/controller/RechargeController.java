package com.gaming.controller;

import com.gaming.dto.request.RechargeRequest;
import com.gaming.dto.response.ApiResponse;
import com.gaming.dto.response.RechargeResponse;
import com.gaming.service.RechargeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recharges")
public class RechargeController {

    private final RechargeService rechargeService;

    public RechargeController(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    // Recharge member
    @PostMapping
    public ResponseEntity<ApiResponse<RechargeResponse>> rechargeMember(@Valid @RequestBody RechargeRequest request) {
        RechargeResponse response = rechargeService.rechargeMember(request);

        String successMessage = String.format(
                "Recharge successful for member %s. Updated balance: %.2f",
                response.getMemberName(),
                response.getUpdatedBalance()
        );

        ApiResponse<RechargeResponse> apiResponse =
                new ApiResponse<>(201, successMessage, response);

        return ResponseEntity.status(201).body(apiResponse);
    }

    // Get all recharges
    @GetMapping
    public ResponseEntity<ApiResponse<List<RechargeResponse>>> getAllRecharges() {
        List<RechargeResponse> list = rechargeService.getAllRecharges();
        ApiResponse<List<RechargeResponse>> apiResponse = new ApiResponse<>(200, "Recharges fetched successfully", list);
        return ResponseEntity.ok(apiResponse);
    }

    // Get recharges by member
    @GetMapping("/member/{memberId}")
    public ResponseEntity<ApiResponse<List<RechargeResponse>>> getRechargesByMember(@PathVariable Long memberId) {
        List<RechargeResponse> list = rechargeService.getRechargesByMember(memberId);
        ApiResponse<List<RechargeResponse>> apiResponse = new ApiResponse<>(200, "Recharges fetched for member successfully", list);
        return ResponseEntity.ok(apiResponse);
    }
}