package com.crazysusanin.planning.repository;

import com.crazysusanin.planning.model.AviaTicketInfo;
import com.crazysusanin.planning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AviaTicketInfoRepository extends JpaRepository<AviaTicketInfo, Integer> {
    List<AviaTicketInfo> getAviaTicketInfoByUser(User user);
}