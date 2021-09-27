package com.crazysusanin.planning.service;


import com.crazysusanin.planning.model.TicketForm;
import com.crazysusanin.planning.repository.TicketFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TicketFormServiceImp implements TicketFormService {

    @Autowired
    TicketFormRepository ticketFormRepository;

    @Override
    public Collection<TicketForm> ticketForm() {
        return ticketFormRepository.findAll();
    }

    @Override
    public TicketForm addTicketForm(TicketForm ticketForm) {
        return ticketFormRepository.save(ticketForm);
    }


}
