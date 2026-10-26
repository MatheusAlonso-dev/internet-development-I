package org.example.controller;

import org.example.model.Agenda;
import org.example.model.Cliente;
import org.example.model.Servico;
import org.example.model.Usuario;
import org.example.service.AgendaService;
import org.example.service.ClienteService;
import org.example.service.ServicoService;
import org.example.service.UsuarioService;
import org.h2.engine.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class AgendaViewController {
    private final AgendaService agendaService;
    private final ServicoService servicoService;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    public AgendaViewController(AgendaService agendaService, ServicoService servicoService, ClienteService clienteService, UsuarioService usuarioService) {
        this.agendaService = agendaService;
        this.servicoService = servicoService;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/telas/agendas")
    public String listar(Model model){
        model.addAttribute("agendas", agendaService.findAll());
        model.addAttribute("servicos", servicoService.findAll());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("editando", false);

        return "agenda";
    }

    @PostMapping("/telas/agendas/salvar")
    public String salvar(
        @RequestParam
        String descricao,
        String data,
        int servicoId,
        int clienteId,
        int usuarioId){

        Agenda newAgenda = new Agenda();
        Servico servico = servicoService.findById(servicoId);
        Cliente cliente = clienteService.findById(clienteId);
        Usuario usuario = usuarioService.findById(usuarioId);

        Date dataAgenda = java.sql.Date.valueOf(data);

        newAgenda.setDescricao(descricao);
        newAgenda.setData(dataAgenda);
        newAgenda.setServico(servico);
        newAgenda.setCliente(cliente);
        newAgenda.setUsuario(usuario);

        agendaService.save(newAgenda);

        return "redirect:/telas/agendas";

    }
    @PostMapping("/telas/agendas/excluir/{id}")
    public String excluir(@PathVariable int id){
        agendaService.deleteById(id);

        return "redirect:/telas/agendas";
    }
    @GetMapping("/telas/agendas/editar/{id}")
    public String listarEditar(@PathVariable int id, Model model){
        Agenda agendaEditar = agendaService.findById(id);

        if(agendaEditar == null){
            return "redirect:/telas/agendas";
        }

        model.addAttribute("agendas", agendaService.findAll());
        model.addAttribute("servicos", servicoService.findAll());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("editando", true);
        model.addAttribute("agendaEditar", agendaEditar);

        return "agenda";
    }

    @PostMapping("/telas/agendas/atualizar/{id}")
    public String atualizar(
            @PathVariable int id,
            @RequestParam
            String descricao,
            String data,
            int servicoId,
            int clienteId,
            int usuarioId){
        Agenda agendaAtualizar = agendaService.findById(id);
        Servico servico = servicoService.findById(servicoId);
        Cliente cliente = clienteService.findById(clienteId);
        Usuario usuario = usuarioService.findById(usuarioId);

        if(agendaAtualizar == null){
            return "redirect:/telas/agenda";
        }

        Date dataAgenda = java.sql.Date.valueOf(data);

        agendaAtualizar.setDescricao(descricao);
        agendaAtualizar.setData(dataAgenda);
        agendaAtualizar.setServico(servico);
        agendaAtualizar.setCliente(cliente);
        agendaAtualizar.setUsuario(usuario);

        agendaService.atualizar(agendaAtualizar);

        return "redirect:/telas/agendas";

    }

}
