package org.example.controller;

import org.example.model.Cliente;
import org.example.model.Endereco;
import org.example.service.ClienteService;
import org.example.service.EnderecoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClienteViewController {
    private final ClienteService clienteService;
    private final EnderecoService enderecoService;

    public ClienteViewController(ClienteService clienteService, EnderecoService enderecoService) {
        this.clienteService = clienteService;
        this.enderecoService = enderecoService;
    }

    @GetMapping("/telas/clientes")
    public String listar(Model model){
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("enderecos", enderecoService.findAll());
        model.addAttribute("editando", false);

        return "cliente";
    }

    @PostMapping("/telas/clientes/salvar")
    public String salvar(
            @RequestParam
            String nome,
            String telefone,
            String email,
            int enderecoId){

        Cliente newCliente = new Cliente();

        Endereco newEndereco = enderecoService.findById(enderecoId);

        newCliente.setNome(nome);
        newCliente.setTelefone(telefone);
        newCliente.setEmail(email);
        newCliente.setEndereco(newEndereco);

        clienteService.save(newCliente);

        return "redirect:/telas/clientes";
    }

    @GetMapping("/telas/clientes/editar/{id}")
    public String listarEditar(@PathVariable int id, Model model){
        Cliente clienteEditar = clienteService.findById(id);

        if(clienteEditar == null){
            return "redirect:/telas/clientes";
        }

        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("enderecos", enderecoService.findAll());
        model.addAttribute("editando", true);
        model.addAttribute("clienteEditar", clienteEditar);

        return "cliente";
    }

    @PostMapping("/telas/clientes/atualizar/{id}")
    public String atualizar(
            @PathVariable int id,
            @RequestParam String nome,
            @RequestParam String telefone,
            @RequestParam String email,
            @RequestParam int enderecoId){

        Cliente clienteAtualizar = clienteService.findById(id);

        if(clienteAtualizar == null){
            return "redirect:/telas/clientes";
        }

        Endereco endereco = enderecoService.findById(enderecoId);

        clienteAtualizar.setNome(nome);
        clienteAtualizar.setTelefone(telefone);
        clienteAtualizar.setEmail(email);
        clienteAtualizar.setEndereco(endereco);

        clienteService.atualizar(clienteAtualizar);

        return "redirect:/telas/clientes";
    }

    @PostMapping("/telas/clientes/excluir/{id}")
    public String excluir(@PathVariable int id){
        clienteService.deleteById(id);

        return "redirect:/telas/clientes";
    }
}
