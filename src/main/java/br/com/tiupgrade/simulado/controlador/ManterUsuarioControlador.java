package br.com.tiupgrade.simulado.controlador;

import br.com.tiupgrade.simulado.dominio.TipoPerfilEnum;
import br.com.tiupgrade.simulado.dominio.Usuario;
import br.com.tiupgrade.simulado.repositorio.TodosOsUsuarios;
import br.com.tiupgrade.simulado.seguranca.Autenticador;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named("manterUsuarioControlador")
@ConversationScoped
public class ManterUsuarioControlador implements Serializable {

    private static final long serialVersionUID = -356325305155548170L;

    private Usuario usuario;

    @Inject
    private Autenticador autenticador;

    private List<Usuario> usuarios = new ArrayList<Usuario>();

    private String senha;

    @Inject
    private Conversation conversation;

    @Inject
    private TodosOsUsuarios todosOsUsuarios;

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

    @PostConstruct
    public void iniciar() {
        usuarios = new ArrayList<Usuario>();
        usuarios.addAll(todosOsUsuarios.obterTodos());
    }

    public String novo() {
        this.usuario = new Usuario();
        senha = "";
        return "usuario_edit";
    }

    public String editar(Usuario usuario) {
        this.usuario = usuario;
        senha = this.usuario.getPassword();
        return "usuario_edit";
    }

    public void mudaStatus(Usuario usuario) {
        usuario.setAtivo(usuario.getAtivo() == null || !usuario.getAtivo());
        todosOsUsuarios.colocar(usuario);
        iniciar();
    }

    public void excluir(Usuario usuario) {
        todosOsUsuarios.remover(usuario);
        iniciar();
    }

    public String salvar() throws Exception {

        if (!senha.isEmpty()) {

            this.usuario.setPassword(autenticador.criptografar(this.senha));
        }
        todosOsUsuarios.colocar(usuario);
        iniciar();
        endConversation();
        return "usuario";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<TipoPerfilEnum> getTiposPerfisEnum() {
        return Arrays.asList(TipoPerfilEnum.values());
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
