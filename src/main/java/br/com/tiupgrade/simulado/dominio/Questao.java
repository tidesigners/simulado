package br.com.tiupgrade.simulado.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by rodrigo on 10/02/17.
 */

@Entity
public class Questao implements Serializable {

    private static final long serialVersionUID = 233735052559683675L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String texto;


}
