package com.hilquiascamelo.launch_service_api.domain.repository;

import com.hilquiascamelo.launch_service_api.domain.model.DailyBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyBalanceRepository extends JpaRepository<DailyBalance, Long>, JpaSpecificationExecutor<DailyBalance> {
}