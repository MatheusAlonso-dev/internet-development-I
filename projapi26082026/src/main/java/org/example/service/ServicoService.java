
package org.example.service;

import org.example.model.Endereco;
import org.example.model.Servico;
import org.example.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public Servico save(Servico servico) {
        return servicoRepository.save(servico);
    }

    public Servico atualizar(Servico servico) {
        return servicoRepository.save(servico);
    }

    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }

    public Servico findById(int id) {
        return servicoRepository.findById(id).orElse(null);
    }

    public void deleteById(int id){ servicoRepository.deleteById(id);}


}
