package com.crazysusanin.planning.repository;

import com.crazysusanin.planning.model.AviaTicketInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AviaTicketInfoRepository extends JpaRepository<AviaTicketInfo, Integer> {
}