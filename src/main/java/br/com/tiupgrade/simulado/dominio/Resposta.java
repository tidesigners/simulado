package br.com.tiupgrade.simulado.dominio;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rodrigosantos on 10/02/17.
 */

@Entity
public class Resposta implements Serializable{

    private static final long serialVersionUID = -5544198958283987531L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String testo;

    @OneToOne
    private Questao questao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }
}
