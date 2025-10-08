package com.gaming.service;

import com.gaming.dto.request.MemberRequest;
import com.gaming.dto.response.MemberResponse;
import com.gaming.exception.ResourceNotFoundException;
import com.gaming.model.Member;
import com.gaming.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Create
    public MemberResponse createMember(MemberRequest request) {
        logger.debug("Creating member: {}", request.getEmail());

        Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());
        member.setPassword(request.getPassword());
        member.setBio(request.getBio());
        member.setBalance(0.0); // default balance

        try {
            Member saved = memberRepository.save(member);
            return mapToResponse(saved);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getMessage().contains("members.email")) {
                throw new DataIntegrityViolationException("Email must be unique. A member with this email already exists.");
            }
            throw ex;
        }
    }

    // Read all
    public List<MemberResponse> getAllMembers() {
        logger.debug("Fetching all members");
        return memberRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Read one
    public MemberResponse getMemberById(Long id) {
        logger.debug("Fetching member id={}", id);
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
        return mapToResponse(member);
    }

    // Update
    public MemberResponse updateMember(Long id, MemberRequest request) {
        logger.debug("Updating member id={}", id);
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));

        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());
        member.setPassword(request.getPassword());
        member.setBio(request.getBio());

        try {
            Member updated = memberRepository.save(member);
            return mapToResponse(updated);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getMessage().contains("members.email")) {
                throw new DataIntegrityViolationException("Email must be unique. A member with this email already exists.");
            }
            throw ex;
        }
    }

    // Delete
    public void deleteMember(Long id) {
        logger.debug("Deleting member id={}", id);
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
        memberRepository.delete(member);
    }

    // Mapper
    private MemberResponse mapToResponse(Member member) {
        MemberResponse r = new MemberResponse();
        r.setId(member.getId());
        r.setName(member.getName());
        r.setEmail(member.getEmail());
        r.setPhone(member.getPhone());
        r.setBio(member.getBio());
        r.setBalance(member.getBalance());
        return r;
    }

    public MemberResponse login(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with email: " + email));

        if (!member.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }

        return mapToResponse(member); // mapToResponse excludes password
    }
}