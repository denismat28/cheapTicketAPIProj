package com.crazysusanin.planning.service;



import com.crazysusanin.planning.model.TicketForm;


import java.util.Collection;

public interface TicketFormService {
Collection<TicketForm> ticketForm();
    TicketForm addTicketForm(TicketForm tickedForm);

}
