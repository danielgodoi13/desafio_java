package com.daniel.service;

import com.daniel.model.Formacao;
import com.daniel.repository.FormacaoRepository;
import com.daniel.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@Service
public class FormacaoService {
    private static final Logger logger = LoggerFactory.getLogger(FormacaoService.class);

    @Autowired
    private FormacaoRepository formacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Formacao> findAll() {
        return formacaoRepository.findAll();
    }

    public Formacao findById(Long id) {
        return formacaoRepository.findById(id)
                .orElseThrow(() -> {
                    String errorMessage = "Nenhum resultado encontrado para o ID: " + id;
                    logger.error(errorMessage);
                    return new ValidationException(errorMessage);
                });
    }

    public Formacao save(@Valid Formacao formacao) {
        validateFormacao(formacao);
        try {
            return formacaoRepository.save(formacao);
        } catch (Exception e) {
            logger.error("Erro inesperado ao salvar formação", e);
            throw new RuntimeException("Erro inesperado ao salvar formação", e);
        }
    }

    public Formacao update(Long id, @Valid Formacao formacao) {
        validateFormacao(formacao);
        formacao.setId(id);
        try {
            return formacaoRepository.save(formacao);
        } catch (Exception e) {
            logger.error("Erro inesperado ao atualizar formação", e);
            throw new RuntimeException("Erro inesperado ao atualizar formação", e);
        }
    }

    public void deleteById(Long id) {
        try {
            formacaoRepository.deleteById(id);
        } catch (Exception e) {
            String errorMessage = "Erro ao deletar formação com ID: " + id;
            logger.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }

    private void validateFormacao(Formacao formacao) {
        validatePessoa(formacao.getPessoa().getId());
        if (formacao.getNomeCurso() == null || formacao.getNomeCurso().isEmpty()) {
            throw new ValidationException("Nome do curso é obrigatório");
        }
        if (formacao.getNivelCurso() == null) {
            throw new ValidationException("Nível do curso é obrigatório");
        }
        if (formacao.getDataConclusao() == null) {
            throw new ValidationException("Data de conclusão é obrigatória");
        }
        if (formacao.getNomeInstituicao() == null || formacao.getNomeInstituicao().isEmpty()) {
            throw new ValidationException("Nome da instituição é obrigatório");
        }
    }

    private void validatePessoa(Long pessoaId) {
        if (!pessoaRepository.existsById(pessoaId)) {
            throw new IllegalArgumentException("Pessoa ID inválido");
        }
    }
}
