package com.flywithingryd.IngrydAirways.controllerMVC;

import com.flywithingryd.IngrydAirways.model.User;
import com.flywithingryd.IngrydAirways.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class CreateUpdateAdminMVC {

    private final UserService userService;

    public CreateUpdateAdminMVC(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/admin-dashboard")
    public String adminPage(){

        return "admin_dashboard";
    }

    @PostMapping("/admin-dashboard")
    public String createAdminNewRole(@ModelAttribute User user){

        return "admin_dashboard";
    }

    @PatchMapping("/admin-dashboard")
    public String updateAdminRole(@ModelAttribute User user){

        return "admin_dashboard";
    }
}
