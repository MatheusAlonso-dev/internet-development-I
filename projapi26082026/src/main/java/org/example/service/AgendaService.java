
package org.example.service;

import org.example.model.Agenda;
import org.example.repository.AgendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;

    public AgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public Agenda save(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public Agenda atualizar(Agenda agenda){return agendaRepository.save(agenda);}

    public List<Agenda> findAll() {
        return agendaRepository.findAll();
    }

    public Agenda findById(int id){return agendaRepository.findById(id).orElse(null);}

    public void deleteById(int id){agendaRepository.deleteById(id);}
}
