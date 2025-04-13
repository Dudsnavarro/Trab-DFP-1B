package com.example.ATT.DF.Controller;

import com.example.ATT.DF.Model.ItemMagico;
import com.example.ATT.DF.Service.ItemMagicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class itemMagicoController {
    @Autowired
    private ItemMagicoService itemService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody ItemMagico item) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemService.cadastrar(item));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<ItemMagico> listar() {
        return itemService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(itemService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
