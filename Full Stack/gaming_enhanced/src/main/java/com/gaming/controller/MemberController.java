package com.gaming.controller;

import com.gaming.dto.request.MemberRequest;
import com.gaming.dto.response.ApiResponse;
import com.gaming.dto.response.MemberResponse;
import com.gaming.exception.ResourceNotFoundException;
import com.gaming.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/members")
@CrossOrigin(origins = "http://localhost:5173")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Create member
    @PostMapping
    public ResponseEntity<ApiResponse<MemberResponse>> createMember(@Valid @RequestBody MemberRequest request) {
        MemberResponse response = memberService.createMember(request);
        ApiResponse<MemberResponse> apiResponse = new ApiResponse<>(201, "Member created successfully", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    // Get all members
    @GetMapping
    public ResponseEntity<ApiResponse<List<MemberResponse>>> getAllMembers() {
        List<MemberResponse> members = memberService.getAllMembers();
        ApiResponse<List<MemberResponse>> apiResponse = new ApiResponse<>(200, "Members fetched successfully", members);
        return ResponseEntity.ok(apiResponse);
    }

    // Get member by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberResponse>> getMemberById(@PathVariable Long id) {
        MemberResponse response = memberService.getMemberById(id);
        ApiResponse<MemberResponse> apiResponse = new ApiResponse<>(200, "Member fetched successfully", response);
        return ResponseEntity.ok(apiResponse);
    }

    // Update member
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberResponse>> updateMember(@PathVariable Long id,
            @Valid @RequestBody MemberRequest request) {
        MemberResponse response = memberService.updateMember(id, request);
        ApiResponse<MemberResponse> apiResponse = new ApiResponse<>(200, "Member updated successfully", response);
        return ResponseEntity.ok(apiResponse);
    }

    // Delete member
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        ApiResponse<Void> apiResponse = new ApiResponse<>(200, "Member deleted successfully", null);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<MemberResponse>> login(@RequestBody MemberRequest request) {
        try {
            MemberResponse member = memberService.login(request.getEmail(), request.getPassword());
            ApiResponse<MemberResponse> apiResponse = new ApiResponse<>(200, "Login successful", member);
            return ResponseEntity.ok(apiResponse);
        } catch (ResourceNotFoundException ex) {
            ApiResponse<MemberResponse> apiResponse = new ApiResponse<>(404, ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        } catch (IllegalArgumentException ex) {
            ApiResponse<MemberResponse> apiResponse = new ApiResponse<>(400, ex.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }
}