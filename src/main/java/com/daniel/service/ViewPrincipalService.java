package com.daniel.service;

import com.daniel.model.ViewPrincipal;
import com.daniel.repository.ViewPrincipalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ViewPrincipalService {

    private static final Logger logger = LoggerFactory.getLogger(ViewPrincipalService.class);

    @Autowired
    private ViewPrincipalRepository repository;

    @Autowired
    private FactService factService;

    public List<ViewPrincipal> findAll() {
        try {
            List<ViewPrincipal> result = repository.findAll();
            List<String> facts = factService.getRandomFacts(result.size());

            Collections.shuffle(facts);
            for (int i = 0; i < result.size(); i++) {
                result.get(i).setFatoAleatorio(facts.get(i));
            }

            return result;
        } catch (Exception e) {
            logger.error("Erro ao buscar dados", e);
            throw new RuntimeException("Erro ao buscar dados", e);
        }
    }
}
