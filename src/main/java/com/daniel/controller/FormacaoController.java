package com.daniel.controller;

import com.daniel.model.Formacao;
import com.daniel.service.FormacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/formacoes")
public class FormacaoController {

    @Autowired
    private FormacaoService formacaoService;

    @GetMapping
    public List<Formacao> findAll() {
        return formacaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Formacao formacao = formacaoService.findById(id);
            return ResponseEntity.ok(formacao);
        } catch (ValidationException e) {
            // Log the error message
            System.err.println(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Formacao formacao) {
        try {
            Formacao savedFormacao = formacaoService.save(formacao);
            return ResponseEntity.ok(savedFormacao);
        } catch (ValidationException e) {
            // Log the error message
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Log unexpected errors
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro inesperado ao salvar formação");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Formacao formacao) {
        try {
            formacao.setId(id);
            Formacao updatedFormacao = formacaoService.update(id, formacao);
            return ResponseEntity.ok(updatedFormacao);
        } catch (ValidationException e) {
            // Log the error message
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Log unexpected errors
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro inesperado ao atualizar formação");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            formacaoService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Log the error message
            String errorMessage = "Erro ao deletar formação com ID: " + id;
            System.err.println(errorMessage);
            e.printStackTrace();
            return ResponseEntity.status(500).body(errorMessage);
        }
    }
}
