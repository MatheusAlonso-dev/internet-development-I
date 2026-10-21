
package org.example.controller;

import org.example.model.Servico;
import org.example.service.ServicoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @PostMapping
    public Servico save(@RequestBody Servico servico) {
        return servicoService.save(servico);
    }

    @GetMapping
    public List<Servico> findAll() {
        return servicoService.findAll();
    }
}
