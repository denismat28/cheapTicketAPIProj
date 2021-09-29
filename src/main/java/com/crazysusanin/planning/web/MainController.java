package com.crazysusanin.planning.web;


import com.crazysusanin.planning.model.AviaTicket;
import com.crazysusanin.planning.model.AviaTicketInfo;
import com.crazysusanin.planning.model.TicketForm;
import com.crazysusanin.planning.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class MainController {
    @Autowired
    AviaTicketService aviaTicketService;

    @Autowired
    private UserService userService;

    @Autowired
    AviaTicketInfoService aviaTicketInfoService;

    @Autowired
    ThreadForPro threadForPro;
    boolean firstSend = true;

    private final TicketFormService tickedFormService;


    public MainController(TicketFormService tickedFormService) {
        this.tickedFormService = tickedFormService;
    }


    @GetMapping({"/", "/main"})
    public String welcome(Model model, Authentication authentication) {


        if (firstSend) {
            try {
                threadForPro.sendMSG();
                firstSend = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("ticketForm", new TicketForm());
        Collection<AviaTicket> aviaTickets = aviaTicketService.findAllByUser(userService.findByUsername(authentication.getName()));
        model.addAttribute("aviaTickets", aviaTickets);

        return "main";
    }

}
