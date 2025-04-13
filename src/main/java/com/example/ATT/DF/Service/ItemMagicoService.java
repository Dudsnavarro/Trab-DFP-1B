package com.example.ATT.DF.Service;

import com.example.ATT.DF.Enum.TipoDeItem;
import com.example.ATT.DF.Model.ItemMagico;
import com.example.ATT.DF.Repository.ItemMagicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemMagicoService {

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    public ItemMagico cadastrar(ItemMagico item) {
        if (item.getTipoDeItem() == TipoDeItem.Arma && item.getDefesa() > 0) {
            throw new IllegalArgumentException("Armas não podem ter defesa.");
        }
        if (item.getTipoDeItem() == TipoDeItem.Armadura && item.getForca() > 0) {
            throw new IllegalArgumentException("Armaduras não podem ter força.");
        }
        if (item.getForca() == 0 && item.getDefesa() == 0) {
            throw new IllegalArgumentException("Item precisa ter pelo menos um atributo maior que zero.");
        }
        if (item.getForca() > 10 || item.getDefesa() > 10) {
            throw new IllegalArgumentException("Força e Defesa não podem ser maiores que 10.");
        }
        return itemMagicoRepository.save(item);
    }

    public List<ItemMagico> listarTodos() {
        return itemMagicoRepository.findAll();
    }

    public ItemMagico buscarPorId(Long id) {
        return itemMagicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item Mágico não encontrado."));
    }
}