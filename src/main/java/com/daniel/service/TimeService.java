package com.daniel.service;

import com.daniel.model.Time;
import com.daniel.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@Service
public class TimeService {
    private static final Logger logger = LoggerFactory.getLogger(TimeService.class);

    @Autowired
    private TimeRepository timeRepository;

    public List<Time> findAll() {
        return timeRepository.findAll();
    }

    public Time findById(Long id) {
        return timeRepository.findById(id)
                .orElseThrow(() -> {
                    String errorMessage = "Nenhum resultado encontrado para o ID: " + id;
                    logger.error(errorMessage);
                    return new ValidationException(errorMessage);
                });
    }

    public Time save(@Valid Time time) {
        try {
            validateTime(time);
            return timeRepository.save(time);
        } catch (ValidationException e) {
            logger.error("Erro ao salvar time: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Erro inesperado ao salvar time", e);
            throw new RuntimeException("Erro inesperado ao salvar time", e);
        }
    }

    public Time update(Long id, @Valid Time time) {
        try {
            validateTime(time);
            time.setId(id);
            return timeRepository.save(time);
        } catch (ValidationException e) {
            logger.error("Erro ao atualizar time: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Erro inesperado ao atualizar time", e);
            throw new RuntimeException("Erro inesperado ao atualizar time", e);
        }
    }

    public void deleteById(Long id) {
        try {
            timeRepository.deleteById(id);
        } catch (Exception e) {
            String errorMessage = "Erro ao deletar time com ID: " + id;
            logger.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }

    private void validateTime(Time time) {
        if (time.getNome() == null || time.getNome().isEmpty()) {
            throw new ValidationException("Nome é obrigatório");
        }
        if (time.getSetor() == null || time.getSetor().isEmpty()) {
            throw new ValidationException("Setor é obrigatório");
        }
    }
}
