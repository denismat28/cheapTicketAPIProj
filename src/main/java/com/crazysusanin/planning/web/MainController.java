package com.crazysusanin.planning.web;


import com.crazysusanin.planning.model.AviaTicket;
import com.crazysusanin.planning.model.AviaTicketInfo;
import com.crazysusanin.planning.service.AviaTicketInfoService;
import com.crazysusanin.planning.service.AviaTicketService;
import com.crazysusanin.planning.service.TicketFormService;
import com.crazysusanin.planning.service.UserService;
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

    private final TicketFormService tickedFormService;


    public MainController( TicketFormService tickedFormService) {
        this.tickedFormService = tickedFormService;
    }



    @GetMapping({"/", "/main"})
    public String welcome(Model model, Authentication authentication) {
        model.addAttribute("ticketForm", new AviaTicketInfo());
        Collection<AviaTicket> aviaTickets = aviaTicketService.findAllByUser(userService.findByUsername(authentication.getName()));
        model.addAttribute("aviaTickets", aviaTickets);

        return "main";
    }

}
