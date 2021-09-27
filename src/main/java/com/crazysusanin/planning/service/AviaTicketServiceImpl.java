package com.crazysusanin.planning.service;

import com.crazysusanin.planning.model.AviaTicket;
import com.crazysusanin.planning.model.AviaTicketInfo;
import com.crazysusanin.planning.model.User;
import com.crazysusanin.planning.repository.AviaTicketRepository;
import com.crazysusanin.planning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
@Service
public class AviaTicketServiceImpl implements AviaTicketService{

    @Autowired
    AviaTicketRepository aviaTicketRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public Collection<AviaTicket> findAllByUser(User user) {
        return aviaTicketRepository.findAllByUser(user);
    }

    @Override
    public void deleteAviaTicketById(int id) {
         aviaTicketRepository.deleteAviaTicketById(id);
    }

    @Override
    public AviaTicket addAviaTicket(AviaTicketInfo aviaTicketInfo) {
        AviaTicket aviaTicket = new AviaTicket();
        aviaTicket.setPrice(aviaTicketInfo.getPrice());
        aviaTicket.setAirline(aviaTicketInfo.getAirline());
        aviaTicket.setFlightNumber(aviaTicketInfo.getFlightNumber());
        aviaTicket.setDepartDate(aviaTicketInfo.getDepartDate());
        aviaTicket.setReturnDate(aviaTicketInfo.getReturnDate());
        aviaTicket.setExpiresAt(aviaTicketInfo.getExpiresAt());
        aviaTicket.setDestination(aviaTicketInfo.getDestination());
        aviaTicket.setOrigin(aviaTicketInfo.getOrigin());
        aviaTicket.setUser(aviaTicketInfo.getUser());
        return aviaTicketRepository.save(aviaTicket);
    }

    @Override
    public AviaTicket findAviaTicketById(int id) {
        return aviaTicketRepository.getOne(id);
    }
}
