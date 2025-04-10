package com.example.ATT.DF.Model;

import com.example.ATT.DF.Enum.TipoDeItem;
import jakarta.persistence.*;

@Entity
@Table(name = "TBL_ITEMMAGICO")
public class ItemMagico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private TipoDeItem tipoDeItem;
    private Long forca;
    private Long defesa;
    @ManyToOne
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;

    public ItemMagico(){}

    public ItemMagico(String nome, TipoDeItem tipoDeItem, Long forca, Long defesa, Personagem personagem){
        this.nome = nome;
        this.tipoDeItem = tipoDeItem;
        this.forca = forca;
        this.defesa = defesa;
        this.personagem = personagem;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public TipoDeItem getTipoDeItem() {return tipoDeItem;}

    public void setTipoDeItem(TipoDeItem tipoDeItem) {this.tipoDeItem = tipoDeItem;}

    public Long getForca() {return forca;}

    public void setForca(Long forca) {this.forca = forca;}

    public Long getDefesa() {return defesa;}

    public void setDefesa(Long defesa) {this.defesa = defesa;}

    public Personagem getPersonagem() {return personagem;}

    public void setPersonagem(Personagem personagem) {this.personagem = personagem;}
}
