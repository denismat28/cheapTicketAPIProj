package com.crazysusanin.planning.service;


import com.crazysusanin.planning.model.AviaTicket;
import com.crazysusanin.planning.model.AviaTicketInfo;
import com.crazysusanin.planning.model.User;

import java.util.Collection;


public interface AviaTicketService {
    Collection<AviaTicket> findAllByUser(User user);
    void deleteAviaTicketById(int id);
    AviaTicket addAviaTicket(AviaTicketInfo aviaTicketInfo);
    AviaTicket findAviaTicketById(int id);


}
