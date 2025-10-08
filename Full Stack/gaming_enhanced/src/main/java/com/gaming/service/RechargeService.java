package com.gaming.service;

import com.gaming.dto.request.RechargeRequest;
import com.gaming.dto.response.RechargeResponse;
import com.gaming.exception.ResourceNotFoundException;
import com.gaming.model.Member;
import com.gaming.model.Recharge;
import com.gaming.repository.MemberRepository;
import com.gaming.repository.RechargeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RechargeService {

    private final RechargeRepository rechargeRepository;
    private final MemberRepository memberRepository;

    public RechargeService(RechargeRepository rechargeRepository, MemberRepository memberRepository) {
        this.rechargeRepository = rechargeRepository;
        this.memberRepository = memberRepository;
    }

    // Create recharge
    public RechargeResponse rechargeMember(RechargeRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + request.getMemberId()));

        double newBalance = member.getBalance() + request.getAmount();
        member.setBalance(newBalance);

        Recharge recharge = new Recharge();
        recharge.setMember(member);
        recharge.setAmount(request.getAmount());
        recharge.setUpdatedBalance(newBalance);
        recharge.setRechargeAt(LocalDateTime.now());

        memberRepository.save(member);
        Recharge saved = rechargeRepository.save(recharge);

        return mapToResponse(saved);
    }

    // Get all recharges
    public List<RechargeResponse> getAllRecharges() {
        return rechargeRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get recharges for specific member
    public List<RechargeResponse> getRechargesByMember(Long memberId) {
        return rechargeRepository.findByMemberId(memberId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Mapper
    private RechargeResponse mapToResponse(Recharge recharge) {
        RechargeResponse r = new RechargeResponse();
        r.setId(recharge.getId());
        r.setMemberId(recharge.getMember().getId());
        r.setMemberName(recharge.getMember().getName());
        r.setAmount(recharge.getAmount());
        r.setUpdatedBalance(recharge.getUpdatedBalance());
        r.setRechargeAt(recharge.getRechargeAt());
        return r;
    }
}