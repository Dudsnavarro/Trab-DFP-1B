package com.example.ATT.DF.Model;

import com.example.ATT.DF.Enum.Classe;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TBL_PERSONAGEM")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String nomeAventureiro;
    private Classe classe;
    private Long level;
    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemMagico> itensMagicos;
    private Long forca;
    private Long defesa;

    public Personagem(){}

    public Personagem(String nome, String nomeAventureiro, Classe classe, Long level, Long forca, Long defesa) {
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.forca = forca;
        this.defesa = defesa;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getNomeAventureiro() {return nomeAventureiro;}

    public void setNomeAventureiro(String nomeAventureiro) {this.nomeAventureiro = nomeAventureiro;}

    public Classe getClasse() {return classe;}

    public void setClasse(Classe classe) {this.classe = classe;}

    public Long getLevel() {return level;}

    public void setLevel(Long level) {this.level = level;}

    public List<ItemMagico> getItensMagicos() {return itensMagicos;}

    public void setItensMagicos(List<ItemMagico> itensMagicos) {this.itensMagicos = itensMagicos;}

    public Long getForca() {return forca;}

    public void setForca(Long forca) {this.forca = forca;}

    public Long getDefesa() {return defesa;}

    public void setDefesa(Long defesa) {this.defesa = defesa;}
}
