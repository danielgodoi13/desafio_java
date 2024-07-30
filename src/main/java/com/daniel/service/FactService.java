package com.daniel.service;

import com.daniel.model.FactResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactService {

    private static final Logger logger = LoggerFactory.getLogger(FactService.class);
    private final RestTemplate restTemplate;

    @Autowired
    public FactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> getRandomFacts(int count) {
        List<String> facts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String apiUrl = "https://uselessfacts.jsph.pl/api/v2/facts/random";
            try {
                ResponseEntity<FactResponse> response = restTemplate.getForEntity(apiUrl, FactResponse.class);
                facts.add(response.getBody().getText());
            } catch (RestClientException e) {
                logger.error("Erro ao buscar fato aleatório da API: {}", apiUrl, e);
                facts.add("Fato não disponível no momento.");
            }
        }
        return facts;
    }
}
