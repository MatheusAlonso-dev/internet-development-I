
package org.example.controller;

import org.example.model.TipoAcesso;
import org.example.service.TipoAcessoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipos-acesso")
public class TipoAcessoController {

    private final TipoAcessoService tipoAcessoService;

    public TipoAcessoController(TipoAcessoService tipoAcessoService) {
        this.tipoAcessoService = tipoAcessoService;
    }

    @PostMapping
    public TipoAcesso save(@RequestBody TipoAcesso tipoAcesso) {
        return tipoAcessoService.save(tipoAcesso);
    }

    @GetMapping
    public List<TipoAcesso> findAll() {
        return tipoAcessoService.findAll();
    }
}
