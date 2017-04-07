package br.com.tiupgrade.simulado.controlador;

import br.com.tiupgrade.simulado.dominio.Curso;
import br.com.tiupgrade.simulado.repositorio.TodosOsCursos;
import br.com.tiupgrade.simulado.util.Message;
import br.com.tiupgrade.simulado.util.Validador;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigo on 13/02/17.
 */

@Named("manterCursoControlador")
@ConversationScoped
public class ManterCursoControlador implements Serializable {

    private static final long serialVersionUID = 804673700728546226L;

    private Curso curso;

    @Inject
    private TodosOsCursos todosOsCursos;

    @Inject
    private Conversation conversation;

    private List<Curso> cursos;

    @PostConstruct
    private void iniciar() {
        cursos = new ArrayList<Curso>();
        cursos.addAll(todosOsCursos.obterTodos());
    }

    public void beginConversation() {
        if (conversation.isTransient()) {
            conversation.setTimeout(1800000L);
            conversation.begin();
        }
    }

    private void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    public String salvar() {

        if (todosOsCursos.colocar(curso)){
            Message.send("Curso salvo com sucesso");
        }else {
            Message.send("Erro ao salvar curso");
        }
        iniciar();
        endConversation();
        return "curso";
    }

    public void excluir(Curso curso) {
        todosOsCursos.remover(curso);
        iniciar();
    }

    public String novo() {
        curso = new Curso();
        return "curso_edit";
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
