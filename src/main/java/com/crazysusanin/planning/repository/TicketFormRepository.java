package com.crazysusanin.planning.repository;


import com.crazysusanin.planning.model.TicketForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketFormRepository extends JpaRepository<TicketForm, Integer> {
}
