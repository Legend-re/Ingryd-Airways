package com.flywithingryd.IngrydAirways.service;

import com.flywithingryd.IngrydAirways.externalAPIs.ExternalAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AirportService {
    private final ExternalAirportService externalAirportService;
    private final LocalAirportService localAirportService;

    @Autowired
    public AirportService(ExternalAirportService externalAirportService, LocalAirportService localAirportService) {
        this.externalAirportService = externalAirportService;
        this.localAirportService = localAirportService;
    }

    public String getAirportName(String code) {
        String name = externalAirportService.getAirportName(code);
        if (!name.equals("Unknown Airport")) {
            return name;
        }
        return localAirportService.getAirportName(code);
    }
}
