package com.example.ATT.DF.Model;

import com.example.ATT.DF.Enum.Classe;
import com.example.ATT.DF.Enum.TipoDeItem;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBL_PERSONAGEM")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nomeAventureiro;

    @Enumerated(EnumType.STRING) // <- ESSENCIAL!
    private Classe classe;

    private Integer level;
    private Integer forca;
    private Integer defesa;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemMagico> itensMagicos = new ArrayList<>(); // <- INICIALIZADO AQUI

    public Personagem() {}

    public Personagem(String nome, String nomeAventureiro, Classe classe, Integer level, Integer forca, Integer defesa) {
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;

        if ((forca + defesa) > 10)
            throw new IllegalArgumentException("A força e defesa do personagem juntas não podem ser maiores que 10.");

        this.level = level;
        this.forca = forca;
        this.defesa = defesa;
    }

    public void adicionarItem(ItemMagico item) {
        if (item.getTipoDeItem() == TipoDeItem.Amuleto) {
            for (ItemMagico existente : itensMagicos) {
                if (existente.getTipoDeItem() == TipoDeItem.Amuleto) {
                    throw new IllegalArgumentException("O personagem só pode possuir 1 amuleto.");
                }
            }
        }
        item.setPersonagem(this);
        this.itensMagicos.add(item);
    }

    public int getForcaTotal() {
        int bonus = itensMagicos.stream().mapToInt(i -> i.getForca() != null ? i.getForca().intValue() : 0).sum();
        return forca + bonus;
    }

    public int getDefesaTotal() {
        int bonus = itensMagicos.stream().mapToInt(i -> i.getDefesa() != null ? i.getDefesa().intValue() : 0).sum();
        return defesa + bonus;
    }


    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getNomeAventureiro() {return nomeAventureiro;}

    public void setNomeAventureiro(String nomeAventureiro) {this.nomeAventureiro = nomeAventureiro;}

    public Classe getClasse() {return classe;}

    public void setClasse(Classe classe) {this.classe = classe;}

    public Integer getLevel() {return level;}

    public void setLevel(Integer level) {this.level = level;}

    public List<ItemMagico> getItensMagicos() {return itensMagicos;}

    public void setItensMagicos(List<ItemMagico> itensMagicos) {this.itensMagicos = itensMagicos;}

    public Integer getForca() {return forca;}

    public void setForca(Integer forca) {this.forca = forca;}

    public Integer getDefesa() {return defesa;}

    public void setDefesa(Integer defesa) {this.defesa = defesa;}
}
