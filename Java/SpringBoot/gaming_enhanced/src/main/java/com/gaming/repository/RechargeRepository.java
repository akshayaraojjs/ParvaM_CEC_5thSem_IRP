package com.gaming.repository;

import com.gaming.model.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RechargeRepository extends JpaRepository<Recharge, Long> {
    List<Recharge> findByMemberId(Long memberId);
}