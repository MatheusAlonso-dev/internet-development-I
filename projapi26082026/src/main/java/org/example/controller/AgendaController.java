
package org.example.controller;

import org.example.model.Agenda;
import org.example.service.AgendaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agendas")
public class AgendaController {

    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @PostMapping
    public Agenda save(@RequestBody Agenda agenda) {
        return agendaService.save(agenda);
    }

    @GetMapping
    public List<Agenda> findAll() {
        return agendaService.findAll();
    }
}
