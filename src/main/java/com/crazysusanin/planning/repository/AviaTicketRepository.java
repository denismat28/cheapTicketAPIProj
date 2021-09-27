package com.crazysusanin.planning.repository;


import com.crazysusanin.planning.model.AviaTicket;
import com.crazysusanin.planning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
    public interface AviaTicketRepository extends JpaRepository<AviaTicket, Integer> {
       Collection<AviaTicket> findAllByUser(User user);
       void deleteAviaTicketById(int id);
    }

