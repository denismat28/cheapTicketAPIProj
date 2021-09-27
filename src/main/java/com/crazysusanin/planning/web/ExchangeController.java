package com.crazysusanin.planning.web;

import com.crazysusanin.planning.model.AviaTicket;
import com.crazysusanin.planning.model.AviaTicketInfo;
import com.crazysusanin.planning.service.AviaTicketService;
import com.crazysusanin.planning.service.UserService;
import com.crazysusanin.planning.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Collection;

import static com.crazysusanin.planning.utils.AppConstants.Currency.*;
import static com.crazysusanin.planning.utils.LoadExchangeNBRB.getCurrency;


@Controller
public class ExchangeController {
    @Autowired
    private UserService userService;
    @Autowired
    AviaTicketService aviaTicketService;

    @RequestMapping(value = "/exchange/{currency}")
    public String saveCurrency(@PathVariable("currency") AppConstants.Currency currency, Authentication authentication, Model model) {

        switch (currency) {
            case BYN:
                double byn = Double.parseDouble(getCurrency("643"));
                     double kByn = byn / 100;
                model.addAttribute("current", BYN);
                    model.addAttribute("k", kByn);
                model.addAttribute("ticketForm", new AviaTicketInfo());
                Collection<AviaTicket> aviaTickets = aviaTicketService.findAllByUser(userService.findByUsername(authentication.getName()));
                model.addAttribute("aviaTickets", aviaTickets);
                return "main";
            case USD:
                double usd = Double.parseDouble(getCurrency("840"));
                double rus = Double.parseDouble(getCurrency("643"));

                    double kUsd = rus /(100 * usd);
                model.addAttribute("current", USD);
                model.addAttribute("k", kUsd);
                model.addAttribute("ticketForm", new AviaTicketInfo());
                Collection<AviaTicket> aviaTicketss = aviaTicketService.findAllByUser(userService.findByUsername(authentication.getName()));
                model.addAttribute("aviaTickets", aviaTicketss);
            return "main";
            case EUR:
                double eur = Double.parseDouble(getCurrency("978"));
                double russ = Double.parseDouble(getCurrency("643"));

                double kEur = russ / (100 * eur);
                model.addAttribute("current", EUR);
                model.addAttribute("k", kEur);
                model.addAttribute("ticketForm", new AviaTicketInfo());
                Collection<AviaTicket> aviaTicketsss = aviaTicketService.findAllByUser(userService.findByUsername(authentication.getName()));
                model.addAttribute("aviaTickets", aviaTicketsss);

            return "main";
            case RUB:
                model.addAttribute("current", RUB);
                model.addAttribute("ticketForm", new AviaTicketInfo());
                Collection<AviaTicket> aviaTicketssss = aviaTicketService.findAllByUser(userService.findByUsername(authentication.getName()));
                model.addAttribute("aviaTickets", aviaTicketssss);

                return "main";
            default:
                return "redirect:/main";
        }

    }
}
