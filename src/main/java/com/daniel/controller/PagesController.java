package com.daniel.controller;

import com.daniel.model.Formacao;
import com.daniel.model.Genero;
import com.daniel.model.Pessoa;
import com.daniel.model.Time;
import com.daniel.model.NivelCurso;
import com.daniel.service.PessoaService;
import com.daniel.service.FormacaoService;
import com.daniel.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@Controller
public class PagesController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private TimeService timeService;

    @Autowired
    private FormacaoService formacaoService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/times")
    public String timesPage(Model model) {
        return "times";
    }

    @GetMapping("/pessoas")
    public String pessoasPage(Model model) {
        return "pessoas";
    }

    @GetMapping("/formacoes")
    public String formacoesPage(Model model) {
        return "formacoes";
    }

    @GetMapping("/time/criar")
    public String addTimePage(Model model) {
        return "criar_time";
    }

    @GetMapping("/pessoa/criar")
    public String addPessoaPage(Model model) {
        return "criar_pessoa";
    }

    @GetMapping("/formacao/criar")
    public String addFormacaoPage(Model model) {
        return "criar_formacao";
    }

    @GetMapping("/time/{id}")
    public String editTimePage(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "editar_time";
    }

    @GetMapping("/pessoa/{id}")
    public String editPessoaPage(@PathVariable Long id, Model model) {
        Pessoa pessoa = pessoaService.findById(id);
        List<Time> times = timeService.findAll();
        List<Genero> generos = Arrays.asList(Genero.values());

        model.addAttribute("pessoa", pessoa);
        model.addAttribute("times", times);
        model.addAttribute("generos", generos);
        return "editar_pessoa";
    }

    @GetMapping("/formacao/{id}")
    public String editFormacaoPage(@PathVariable Long id, Model model) {
        Formacao formacao = formacaoService.findById(id);
        List<Pessoa> pessoas = pessoaService.findAll();
        List<NivelCurso> niveisCurso = Arrays.asList(NivelCurso.values());

        model.addAttribute("formacao", formacao);
        model.addAttribute("pessoas", pessoas);
        model.addAttribute("niveisCurso", niveisCurso);
        return "editar_formacao";
    }
}
