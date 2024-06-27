package com.flywithingryd.IngrydAirways.controllerMVC;

import com.flywithingryd.IngrydAirways.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class FlightControllerMVC {
    private final UserService userService;

    public FlightControllerMVC(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/flight")
    public String showFlightPage(Model model){
        model.addAttribute("login");
        return "create_flight";
    }

    @GetMapping("/search-flight")
    public String searchFlightPage(){

        return "show_search_flight";
    }
    @GetMapping("/edit-flight")
    public String editFlight(){
        return "edit_flight";
    }
}
