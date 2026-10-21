
package org.example.service;

import org.example.model.TipoAcesso;
import org.example.repository.TipoAcessoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoAcessoService {

    private final TipoAcessoRepository tipoAcessoRepository;

    public TipoAcessoService(TipoAcessoRepository tipoAcessoRepository) {
        this.tipoAcessoRepository = tipoAcessoRepository;
    }

    public TipoAcesso save(TipoAcesso tipoAcesso) {
        return tipoAcessoRepository.save(tipoAcesso);
    }

    public List<TipoAcesso> findAll() {
        return tipoAcessoRepository.findAll();
    }
}
