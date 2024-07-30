package com.daniel.controller;

import com.daniel.model.Pessoa;
import com.daniel.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> findAll() {
        return pessoaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Pessoa pessoa = pessoaService.findById(id);
            return ResponseEntity.ok(pessoa);
        } catch (ValidationException e) {
            // Log the error message
            System.err.println(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Pessoa pessoa) {
        try {
            Pessoa savedPessoa = pessoaService.save(pessoa);
            return ResponseEntity.ok(savedPessoa);
        } catch (ValidationException e) {
            // Log the error message
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Log unexpected errors
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro inesperado ao salvar pessoa");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
        try {
            pessoa.setId(id);
            Pessoa updatedPessoa = pessoaService.update(id, pessoa);
            return ResponseEntity.ok(updatedPessoa);
        } catch (ValidationException e) {
            // Log the error message
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Log unexpected errors
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro inesperado ao atualizar pessoa");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            pessoaService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Log the error message
            String errorMessage = "Erro ao deletar pessoa com ID: " + id;
            System.err.println(errorMessage);
            e.printStackTrace();
            return ResponseEntity.status(500).body(errorMessage);
        }
    }
}
