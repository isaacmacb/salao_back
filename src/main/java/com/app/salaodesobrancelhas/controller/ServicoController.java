package com.app.salaodesobrancelhas.controller;

import com.app.salaodesobrancelhas.entity.Servico;
import com.app.salaodesobrancelhas.service.ServicoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/servicos")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public String listar(org.springframework.ui.Model model) {
        model.addAttribute("servicos", servicoService.listarTodos());
        return "servicos/lista";
    }

    @GetMapping("/novo")
    public String novo(org.springframework.ui.Model model) {
        model.addAttribute("servico", new Servico());
        return "servicos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Servico servico) {
        servicoService.salvar(servico);
        return "redirect:/servicos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, org.springframework.ui.Model model) {
        model.addAttribute("servico", servicoService.buscarPorId(id));
        return "servicos/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        servicoService.deletar(id);
        return "redirect:/servicos";
    }

}

