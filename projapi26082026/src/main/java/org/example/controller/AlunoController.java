package org.example.controller;

import org.example.model.Aluno;
import org.example.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AlunoController {
    private static final String ENDPOINT = "uniara/api/v1/aluno";

    @Autowired
    private AlunoService alunoService;

    @PostMapping(ENDPOINT)
    public Aluno save(Aluno aluno){
        return alunoService.save(aluno);
    }
    @GetMapping(ENDPOINT)
    public List<Aluno> findAll(){
        return alunoService.findAll();
    }
}
