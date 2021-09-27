package com.crazysusanin.planning.web;

import com.crazysusanin.planning.model.User;
import com.crazysusanin.planning.service.SecurityService;
import com.crazysusanin.planning.service.UserService;
import com.crazysusanin.planning.utils.AppConstants;
import com.crazysusanin.planning.utils.EmailUtils;
import com.crazysusanin.planning.utils.EncryptDecrypt;
import com.crazysusanin.planning.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private EncryptDecrypt ed;



    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";

        }
        userService.save(userForm);


        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        if(userService.save(userForm)) {
            // send activation instructions
            String token = ed.encrypt(userForm.getUsername());

            EmailUtils.sendMessage(userForm.getEmail(), AppConstants.EMAIL_ID,
                    "CRAZY USERS: Activation",
                    "<a href='" +AppConstants.MAIN_URL+"/activate/"
                            +token +"'>ACTIVATE</a>");

            return "redirect:/login";
        } else {

            return "redirect:/registration";
        }

    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @PostMapping("/login")
    public String fromLogin(Model model) {

        return "redirect:/main";
    }



    @RequestMapping({"/activate/{token}"})
    public String activate(@PathVariable("token") String token) {
//activate user
   String decryptToken = ed.decrypt(token);
    userService.activateUser(decryptToken);

        return "redirect:/main";
    }



}
