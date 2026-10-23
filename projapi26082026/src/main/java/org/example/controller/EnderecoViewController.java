package org.example.controller;

import org.example.model.Endereco;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.example.service.EnderecoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EnderecoViewController {
    private final EnderecoService enderecoService;

    public EnderecoViewController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/telas/enderecos")
    public String listar(Model model){
        model.addAttribute("enderecos",enderecoService.findAll());
        model.addAttribute("editando",false);
        return "endereco";
    }
    @GetMapping("/telas/enderecos/editar/{id}")
    public String listarEditar(@PathVariable int id, Model model){
        Endereco endereco = enderecoService.findById(id);

        if( endereco == null){
            return "redirect:/telas/enderecos";
        }

        model.addAttribute( "enderecos", enderecoService.findAll());
        model.addAttribute("enderecoEditar", endereco);
        model.addAttribute("editando", true);
        return "endereco";
    }

    @PostMapping("/telas/enderecos/atualizar/{id}")
    public String atualizar(@PathVariable int id, Endereco endereco) {
        endereco.setId(id);
        enderecoService.atualizar(endereco);

        return "redirect:/telas/enderecos";
    }


    @PostMapping("/telas/enderecos/salvar")
    public String salvar(
            @RequestParam
            String logradouro,
            String uf,
            String estado,
            String cep,
            String cidade,
            int numero,
            String complemento){

        Endereco endereco = new Endereco();

        endereco.setLogradouro(logradouro);
        endereco.setUf(uf);
        endereco.setEstado(estado);
        endereco.setCep(cep);
        endereco.setCidade(cidade);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        enderecoService.save(endereco);

        return "redirect:/telas/enderecos";

    }

    @PostMapping("/telas/enderecos/excluir/{id}")
    public String excluir(@PathVariable int id){
        enderecoService.deleteById(id);
        return "redirect:/telas/enderecos";
    }
}
