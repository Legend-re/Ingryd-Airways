package com.flywithingryd.IngrydAirways.controllerMVC;

import com.flywithingryd.IngrydAirways.model.Reservation;
import com.flywithingryd.IngrydAirways.model.User;
import com.flywithingryd.IngrydAirways.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class FrontEndController {

    private final UserService userService;

    public FrontEndController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("reservation", new Reservation());
        return "home";
    }

    @PostMapping("/admin-login")
    public String adminLogin(String email, String password){
        userService.loginUser(email, password);

        return "redirect:/api/admin-dashboard";
    }



}
