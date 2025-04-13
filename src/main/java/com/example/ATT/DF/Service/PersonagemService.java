package com.example.ATT.DF.Service;

import com.example.ATT.DF.Enum.TipoDeItem;
import com.example.ATT.DF.Model.ItemMagico;
import com.example.ATT.DF.Model.Personagem;
import com.example.ATT.DF.Repository.ItemMagicoRepository;
import com.example.ATT.DF.Repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    public Personagem criar(Personagem personagem) {
        long soma = personagem.getForca() + personagem.getDefesa();
        if (soma > 10) {
            throw new IllegalArgumentException("Força e Defesa não podem ultrapassar 10 pontos.");
        }
        return personagemRepository.save(personagem);
    }

    public List<Personagem> listarTodos() {
        return personagemRepository.findAll();
    }

    public Personagem buscarPorId(Long id) {
        return personagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado."));
    }

    public Personagem atualizarNomeAventureiro(Long id, String novoNomeAventureiro) {
        Personagem personagem = buscarPorId(id);
        personagem.setNomeAventureiro(novoNomeAventureiro);
        return personagemRepository.save(personagem);
    }

    public void remover(Long id) {
        personagemRepository.deleteById(id);
    }

    public Personagem adicionarItem(Long personagemId, ItemMagico item) {
        Personagem personagem = buscarPorId(personagemId);
        personagem.adicionarItem(item);
        itemMagicoRepository.save(item);
        return personagemRepository.save(personagem);
    }

    public List<ItemMagico> listarItensDoPersonagem(Long personagemId) {
        return buscarPorId(personagemId).getItensMagicos();
    }

    public void removerItemDoPersonagem(Long personagemId, Long itemId) {
        Personagem personagem = buscarPorId(personagemId);
        ItemMagico item = itemMagicoRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        // Remove da lista do personagem
        personagem.getItensMagicos().removeIf(i -> i.getId().equals(itemId));
        personagemRepository.save(personagem);

        // Remove vínculo do personagem no item antes de deletar
        item.setPersonagem(null);
        itemMagicoRepository.save(item);

        // Agora pode excluir
        itemMagicoRepository.deleteById(itemId);
    }

    public ItemMagico buscarAmuletoDoPersonagem(Long personagemId) {
        return buscarPorId(personagemId)
                .getItensMagicos()
                .stream()
                .filter(item -> item.getTipoDeItem() == TipoDeItem.Amuleto)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Este personagem não possui amuleto."));
    }
}