package com.daniel.controller;

import com.daniel.service.FactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@Controller
public class FactController {

    private static final Logger logger = LoggerFactory.getLogger(FactController.class);
    private final FactService factService;

    @Autowired
    public FactController(FactService factService) {
        this.factService = factService;
    }

    @GetMapping("/main")
    public String showMainPage(Model model) {
        try {
            List<String> facts = factService.getRandomFacts(100); // Busca 100 fatos
            Collections.shuffle(facts); // Distribui aleatoriamente
            model.addAttribute("facts", facts);
        } catch (Exception e) {
            logger.error("Erro ao carregar a página principal", e);
            model.addAttribute("error", "Erro ao carregar fatos aleatórios.");
        }
        return "main";
    }
}
