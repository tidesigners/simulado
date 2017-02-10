package br.com.tiupgrade.simulado.dominio;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by rodrigosantos on 10/02/17.
 */

@Entity
public class Materia implements Serializable {

    private static final long serialVersionUID = 2826485952811656857L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @OneToMany
    private List<Questao> questoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }
}
