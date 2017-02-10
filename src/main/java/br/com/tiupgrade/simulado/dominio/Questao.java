package br.com.tiupgrade.simulado.dominio;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by rodrigosantos on 10/02/17.
 */

@Entity
public class Questao implements Serializable {

    private static final long serialVersionUID = 8515948638414654251L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String texto;

    @OneToMany
    private List<Resposta> resposta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<Resposta> getResposta() {
        return resposta;
    }

    public void setResposta(List<Resposta> resposta) {
        this.resposta = resposta;
    }
}
