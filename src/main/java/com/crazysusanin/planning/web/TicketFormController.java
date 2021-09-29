package com.crazysusanin.planning.web;


import com.crazysusanin.planning.model.AviaTicketInfo;
import com.crazysusanin.planning.model.TicketForm;
import com.crazysusanin.planning.model.User;
import com.crazysusanin.planning.service.AviaTicketInfoService;
import com.crazysusanin.planning.service.AviaTicketService;
import com.crazysusanin.planning.service.TicketFormService;
import com.crazysusanin.planning.service.UserService;
import com.crazysusanin.planning.utils.AppConstants;
import com.crazysusanin.planning.utils.EmailUtils;
import com.crazysusanin.planning.utils.GeneratePDF;
import com.crazysusanin.planning.utils.SendHTTPRequest;
import com.itextpdf.layout.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class TicketFormController {
    @Autowired
    private UserService userService;

    @Autowired
    AviaTicketInfoService aviaTicketInfoService;

    @Autowired
    AviaTicketService aviaTicketService;

    private final TicketFormService tickedFormService;

    @Autowired
     GeneratePDF generatePDF;

    public TicketFormController( TicketFormService tickedFormService) {
        this.tickedFormService = tickedFormService;
    }


    @GetMapping({"/addTicketForm"})
    public String showTicketForm(Model model) {
        model.addAttribute("ticketForm", new AviaTicketInfo());

        return "ticket_form";
    }


    @PostMapping("/addTicketForm")
    public String addTicketForm(@ModelAttribute("ticketForm") TicketForm ticketForm, Model model, Authentication authentication) {

        TicketForm savedTicketForm = tickedFormService.addTicketForm(ticketForm);

        //build URI request
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host("api.travelpayouts.com")
                .path("/v1/prices/direct").query("origin={origin}").query("Destination={Destination}").query("depart_date={depart_date}")
                .query("return_date={return_date}").query("token={token}")
                .buildAndExpand(ticketForm.getOrigin(), ticketForm.getDestination(),
                        ticketForm.getDepartDate(), ticketForm.getReturnDate(), AppConstants.TOKEN);

        System.out.println(uriComponents);

        //send URI and adding attribute
        try {
            AviaTicketInfo   aviaTicketInfo = SendHTTPRequest.callMe(uriComponents.toString());
            aviaTicketInfo.setOrigin(ticketForm.getOrigin());
            aviaTicketInfo.setDestination(ticketForm.getDestination());
            aviaTicketInfo.setUser(userService.findByUsername(authentication.getName()));
            aviaTicketInfoService.addAviaTicketInfo(aviaTicketInfo);

            List<AviaTicketInfo> aviaTicketInfos = new ArrayList<>();
            aviaTicketInfos.add(aviaTicketInfo);
            model.addAttribute("aviaTickets", aviaTicketInfos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ticket_form";
    }

    @RequestMapping(value = "/saveTicket/{id}")
    public String saveAviaTicket(@PathVariable (name = "id") int id){
        aviaTicketService.addAviaTicket(aviaTicketInfoService.findAviaTicketInfoById(id));
        return "redirect:/main";
    }

    @RequestMapping(value = "/deleteTicket/{id}")
    public String deleteAviaTicket(@PathVariable (name = "id") int id){
        aviaTicketService.deleteAviaTicketById(id);
        return "redirect:/main";
    }

    @RequestMapping("/share/{id}")
    public String shareTicketViaEmail(@PathVariable (name = "id") int id, Authentication authentication) {

        User user = userService.findByUsername(authentication.getName());
       //generate PDF and send message
        try {
        Document doc = generatePDF.generatePDFByAviaTicket(id);
            EmailUtils.sendMessageWithPDF(user.getEmail(), AppConstants.EMAIL_ID,
                    "CRAZY USERS: Your ticket", "D:\\generate_pdf\\test.pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/main";
    }
}
