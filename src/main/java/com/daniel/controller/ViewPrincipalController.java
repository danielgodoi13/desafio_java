package com.daniel.controller;

import com.daniel.model.ViewPrincipal;
import com.daniel.service.ViewPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class ViewPrincipalController {

    private static final Logger logger = LoggerFactory.getLogger(ViewPrincipalController.class);

    @Autowired
    private ViewPrincipalService service;

    @GetMapping("/api/pessoas-formacoes")
    public ResponseEntity<?> getPessoasFormacoes() {
        try {
            List<ViewPrincipal> result = service.findAll();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("Erro ao buscar dados", e);
            return ResponseEntity.status(500).body("Erro ao buscar dados");
        }
    }
}
