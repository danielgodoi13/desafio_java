package com.daniel.service;

import com.daniel.model.Pessoa;
import com.daniel.repository.PessoaRepository;
import com.daniel.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@Service
public class PessoaService {
    private static final Logger logger = LoggerFactory.getLogger(PessoaService.class);

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TimeRepository timeRepository;

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public Pessoa findById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> {
                    String errorMessage = "Nenhum resultado encontrado para o ID: " + id;
                    logger.error(errorMessage);
                    return new ValidationException(errorMessage);
                });
    }

    public Pessoa save(@Valid Pessoa pessoa) {
        validatePessoa(pessoa);
        validateTime(pessoa.getTime().getId());
        try {
            return pessoaRepository.save(pessoa);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = "CPF já cadastrado";
            logger.error(errorMessage, e);
            throw new ValidationException(errorMessage);
        } catch (Exception e) {
            logger.error("Erro inesperado ao salvar pessoa", e);
            throw new RuntimeException("Erro inesperado ao salvar pessoa", e);
        }
    }

    public Pessoa update(Long id, @Valid Pessoa pessoa) {
        validatePessoa(pessoa);
        validateTime(pessoa.getTime().getId());
        pessoa.setId(id);
        try {
            return pessoaRepository.save(pessoa);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = "CPF já cadastrado";
            logger.error(errorMessage, e);
            throw new ValidationException(errorMessage);
        } catch (Exception e) {
            logger.error("Erro inesperado ao atualizar pessoa", e);
            throw new RuntimeException("Erro inesperado ao atualizar pessoa", e);
        }
    }

    public void deleteById(Long id) {
        try {
            pessoaRepository.deleteById(id);
        } catch (Exception e) {
            String errorMessage = "Erro ao deletar pessoa com ID: " + id;
            logger.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }

    private void validateTime(Long timeId) {
        if (!timeRepository.existsById(timeId)) {
            throw new IllegalArgumentException("Time ID inválido");
        }
    }

    private void validatePessoa(Pessoa pessoa) {
        if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            throw new ValidationException("Nome é obrigatório");
        }
        if (pessoa.getCpf() == null || !pessoa.getCpf().matches("\\d{11}")) {
            throw new ValidationException("CPF deve conter 11 dígitos");
        }
        if (pessoa.getDataNascimento() == null) {
            throw new ValidationException("Data de nascimento é obrigatória");
        }
        if (pessoa.getGenero() == null) {
            throw new ValidationException("Gênero é obrigatório");
        }
        if (pessoa.getTelefone() == null || !pessoa.getTelefone().matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}")) {
            throw new ValidationException("Telefone deve estar no formato (XX) XXXXX-XXXX");
        }
        if (pessoa.getEmail() == null || !pessoa.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new ValidationException("Email deve ser válido");
        }
    }
}
