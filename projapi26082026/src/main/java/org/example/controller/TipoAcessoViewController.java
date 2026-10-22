package org.example.controller;

import org.example.model.TipoAcesso;
import org.example.service.TipoAcessoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TipoAcessoViewController {

    private final TipoAcessoService tipoAcessoService;

    public TipoAcessoViewController(TipoAcessoService tipoAcessoService) {
        this.tipoAcessoService = tipoAcessoService;
    }

    @GetMapping("/telas/tipos-acesso")
    public String listar(Model model) {
        model.addAttribute(
                "tiposAcesso",
                tipoAcessoService.findAll()
        );

        return "tipo-acesso";
    }

    @PostMapping("/telas/tipos-acesso/salvar")
    public String salvar(
            @RequestParam String descricao,
            RedirectAttributes redirectAttributes) {

        TipoAcesso tipoAcesso = new TipoAcesso();
        tipoAcesso.setDescricao(descricao);

        tipoAcessoService.save(tipoAcesso);

        redirectAttributes.addFlashAttribute(
                "mensagem",
                "Tipo de acesso cadastrado com sucesso!"
        );

        return "redirect:/telas/tipos-acesso";
    }

    @PostMapping("/telas/tipos-acesso/excluir/{id}")
    public String excluir(@PathVariable int id) {
        tipoAcessoService.deleteById(id);
        return "redirect:/telas/tipos-acesso";
    }
}