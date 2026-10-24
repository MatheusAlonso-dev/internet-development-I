package org.example.controller;

import org.example.model.TipoAcesso;
import org.example.model.Usuario;
import org.example.service.TipoAcessoService;
import org.example.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class UsuarioViewController {
    private final UsuarioService usuarioService;
    private final TipoAcessoService tipoAcessoService;

    public UsuarioViewController(UsuarioService usuarioService, TipoAcessoService tipoAcessoService) {
        this.usuarioService = usuarioService;
        this.tipoAcessoService = tipoAcessoService;
    }

    @GetMapping("/telas/usuarios")
    public String listar(Model model){
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("tiposAcesso", tipoAcessoService.findAll());
        model.addAttribute("editando", false);
        return "usuario";
    }

    @PostMapping("/telas/usuarios/salvar")
    public String salvar(
            @RequestParam
            String usuario,
            String senha,
            int tipoAcessoId){

        Usuario newUsuario = new Usuario();

        TipoAcesso tipo = tipoAcessoService.findById(tipoAcessoId);
        Date dataAtual = new Date();

        newUsuario.setUsuario(usuario);
        newUsuario.setSenha(senha);
        newUsuario.setDataCadastro(dataAtual);
        newUsuario.setTipoAcesso(tipo);
        usuarioService.save(newUsuario);

        return "redirect:/telas/usuarios";
    }

    @PostMapping("/telas/usuarios/excluir/{id}")
    public String excluir(@PathVariable int id){
        usuarioService.deleteById(id);

        return "redirect:/telas/usuarios";

    }

    @GetMapping("/telas/usuarios/editar/{id}")
    public String listarEditar(@PathVariable int id, Model model){
        Usuario usuario = usuarioService.findById(id);


        if(usuario == null){
            return "redirect:/telas/usuarios";
        }

        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("tiposAcesso", tipoAcessoService.findAll());
        model.addAttribute("usuarioEditar", usuario);
        model.addAttribute("editando", true);

        return "usuario";
    }

    @PostMapping("/telas/usuarios/atualizar/{id}")
    public String atualizar(
            @PathVariable int id,
            @RequestParam int tipoAcessoId,
            @RequestParam String usuario,
            @RequestParam String senha) {

        Usuario usuarioAtualizar = usuarioService.findById(id);

        if (usuarioAtualizar == null) {
            return "redirect:/telas/usuarios";
        }

        TipoAcesso tipo = tipoAcessoService.findById(tipoAcessoId);

        usuarioAtualizar.setUsuario(usuario);
        usuarioAtualizar.setSenha(senha);
        usuarioAtualizar.setTipoAcesso(tipo);

        usuarioService.atualizar(usuarioAtualizar);

        return "redirect:/telas/usuarios";
    }
}
