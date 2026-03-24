package com.tavares.gerenciamentoprojetos.controllers;

import com.tavares.gerenciamentoprojetos.models.Projeto;
import com.tavares.gerenciamentoprojetos.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {
    @Autowired
    private ProjetoService service;

    @PostMapping
    public ResponseEntity<Projeto> salvar(@RequestBody Projeto projeto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(projeto));
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Projeto> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
