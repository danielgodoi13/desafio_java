package com.daniel.controller;

import com.daniel.model.Time;
import com.daniel.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/times")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping
    public List<Time> findAll() {
        return timeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Time time = timeService.findById(id);
            return ResponseEntity.ok(time);
        } catch (ValidationException e) {
            // Log the error message
            System.err.println(e.getMessage());
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Time time) {
        try {
            Time savedTime = timeService.save(time);
            return ResponseEntity.ok(savedTime);
        } catch (ValidationException e) {
            // Log the error message
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Log unexpected errors
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro inesperado ao salvar o time");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Time time) {
        try {
            time.setId(id);
            Time updatedTime = timeService.update(id, time);
            return ResponseEntity.ok(updatedTime);
        } catch (ValidationException e) {
            // Log the error message
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Log unexpected errors
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro inesperado ao atualizar o time");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            timeService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Log the error message
            String errorMessage = "Erro ao deletar time com ID: " + id;
            System.err.println(errorMessage);
            e.printStackTrace();
            return ResponseEntity.status(500).body(errorMessage);
        }
    }
}
