package com.flywithingryd.IngrydAirways.externalAPIs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ExternalAirportService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${api.base.url}")
    private String apiUrlBase;

    @Value("${api.token}")
    private String apiToken;

    public String getAirportName(String code) {
        if (apiUrlBase == null) {
            return "Unknown Airport";
        }

        String url = String.format("%s/airport/%s?apiToken=%s", apiUrlBase, code, apiToken);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            return response.getBody();
        } catch (Exception e) {
            System.out.println("Error fetching airport data from external API: " + e.getMessage());
            return "Unknown Airport";
        }
    }
}

