package org.example.controller;

import org.example.model.Endereco;
import org.example.model.Servico;
import org.springframework.ui.Model;
import org.example.service.ServicoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ServicoViewController {
    private final ServicoService servicoService;

    public ServicoViewController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping("/telas/servicos")
    public String listar(Model model){
        model.addAttribute("servicos", servicoService.findAll());
        model.addAttribute("editando", false);

        return "servico";
    }

    @GetMapping("/telas/servicos/editar/{id}")
    public String listarEditar(@PathVariable int id, Model model){
        Servico servico = servicoService.findById(id);

        if(servico == null){
            return "redirect: /telas/servicos";
        }

        model.addAttribute("servicos", servicoService.findAll());
        model.addAttribute("servicoEditar", servico);
        model.addAttribute("editando", true);

        return "servico";
    }

    @PostMapping("/telas/servicos/atualizar/{id}")
    public String atualizar(@PathVariable int id, Servico servico){
        servico.setId(id);
        servicoService.atualizar(servico);

        return "redirect:/telas/servicos";
    }

    @PostMapping("/telas/servicos/salvar")
    public String salvar(
            @RequestParam
            String nome,
            double valor){
            Servico servico = new Servico();

            servico.setNome(nome);
            servico.setValor(valor);
            servicoService.save(servico);

            return "redirect:/telas/servicos";
    }

    @PostMapping("/telas/servicos/excluir/{id}")
    public String excluir(@PathVariable int id){
        servicoService.deleteById(id);

        return "redirect:/telas/servicos";
    }
}
