package com.linocks.registration.resource;

import com.linocks.registration.model.Users;
import com.linocks.registration.service.NotificationService;
import com.linocks.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;


    @GetMapping("/")
    public String loadHomePage(Model model){
        model.addAttribute("users", userService.getAllUsers());

        return "home";
    }


    @GetMapping("/registration")
    public String loadRegistrationPage(Model model){
        model.addAttribute("user",new Users());
        model.addAttribute("users", userService.getAllUsers());

        return "registration";
    }


    @PostMapping("/registration")
    public String saveRegistrationDetails(@Valid Users user, BindingResult bindingResult, RedirectAttributes redirectAttrib){

        if(bindingResult.hasErrors()){
            return "registration";
        }

        userService.saveUser(user);
        notificationService.sendRegistrationEmail(user.getEmail());

        redirectAttrib.addFlashAttribute("success",true);

        return "redirect:/registration";
    }
}
