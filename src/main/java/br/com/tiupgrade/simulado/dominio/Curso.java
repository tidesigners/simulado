package br.com.tiupgrade.simulado.dominio;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by rodrigosantos on 10/02/17.
 */

@Entity
public class Curso implements Serializable {

    private static final long serialVersionUID = 2337871788021882758L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer codigo;

    private String descricao;

    @OneToMany
    private List<Materia> materias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
}
