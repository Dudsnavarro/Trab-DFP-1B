package com.example.ATT.DF.Controller;

import com.example.ATT.DF.Model.ItemMagico;
import com.example.ATT.DF.Model.Personagem;
import com.example.ATT.DF.Service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Personagem personagem) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(personagemService.criar(personagem));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Personagem> listar() {
        return personagemService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(personagemService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/nome-aventureiro")
    public ResponseEntity<?> atualizarNomeAventureiro(@PathVariable Long id, @RequestParam String nome) {
        try {
            return ResponseEntity.ok(personagemService.atualizarNomeAventureiro(id, nome));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        personagemService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/adicionar-item")
    public ResponseEntity<?> adicionarItem(@PathVariable Long id, @RequestBody ItemMagico item) {
        try {
            return ResponseEntity.ok(personagemService.adicionarItem(id, item));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/itens")
    public List<ItemMagico> listarItens(@PathVariable Long id) {
        return personagemService.listarItensDoPersonagem(id);
    }

    @DeleteMapping("/{personagemId}/item/{itemId}")
    public ResponseEntity<?> removerItem(@PathVariable Long personagemId, @PathVariable Long itemId) {
        personagemService.removerItemDoPersonagem(personagemId, itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/amuleto")
    public ResponseEntity<?> buscarAmuleto(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(personagemService.buscarAmuletoDoPersonagem(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
