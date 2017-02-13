package br.com.tiupgrade.simulado.seguranca;

import br.com.tiupgrade.simulado.dominio.TipoModuloEnum;
import br.com.tiupgrade.simulado.dominio.Usuario;
import br.com.tiupgrade.simulado.repositorio.TodosOsUsuarios;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.security.MessageDigest;

@SessionScoped
@Named("autenticador")
public class Autenticador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    TodosOsUsuarios todosOsUsuarios;
    private String login;
    private String senha;
    private boolean logado;

    private Usuario usuario;

    @PostConstruct
    public void inicializar() {
        login = "";
        senha = "";
        logado = false;
    }

    public String doLogin() {

        Usuario usuarioValidador = todosOsUsuarios.obterPorLogin(login);
        if (usuarioValidador != null) {
            try {
                if (usuarioValidador.getPassword().equals(this.criptografar(senha))) {
                    if (!usuarioValidador.getAtivo()) {
                        FacesMessage msg = new FacesMessage("Este usuário está inativo!", "Entre em contato com o administrador do sistema");
                        msg.setSeverity(FacesMessage.SEVERITY_WARN);
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        return "login";
                    }
                    usuario = usuarioValidador;
                    logado = true;

                    FacesMessage msg = new FacesMessage("Bem-vindo ".concat(usuario.getNome()).concat("!"), "INFO MSG");
                    msg.setSeverity(FacesMessage.SEVERITY_INFO);
                    FacesContext.getCurrentInstance().addMessage(null, msg);

                    return "home";
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário ou Senha inválido!", "");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "login";
    }

    public String doLogout() {

        logado = false;
        usuario = null;

        // Set logout message
        FacesMessage msg = new FacesMessage("Desconectado com sucesso!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    public String criptografar(String senha) throws Exception {
        MessageDigest algorithm;
        StringBuilder senhaCriptografada = new StringBuilder();
        try {
            algorithm = MessageDigest.getInstance("MD5");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

            for (byte b : messageDigest) {
                senhaCriptografada.append(String.format("%02X", 0xFF & b));
            }

        } catch (Exception e) {
            throw new Exception("Não foi possível criptografar");
        }

        return senhaCriptografada.toString();
    }

    public boolean podeEditar(String modulo) {
        if (usuario == null) {
            return false;
        }

        TipoModuloEnum m = TipoModuloEnum.valueOf(modulo);

        switch (usuario.getPerfil()) {
            case ADMINISTRADOR:
                return m.getPermissao().substring(0, 1).equals("2");
            case GESTOR:
                return m.getPermissao().substring(1, 2).equals("2");
            case USUARIO:
                return m.getPermissao().substring(2, 3).equals("2");
        }

        return false;
    }


    public boolean podeVer(String modulo) {
        if (usuario == null) {
            return false;
        }

        TipoModuloEnum m = TipoModuloEnum.valueOf(modulo);

        switch (usuario.getPerfil()) {
            case ADMINISTRADOR:
                return !m.getPermissao().substring(0, 1).equals("0");
            case GESTOR:
                return !m.getPermissao().substring(1, 2).equals("0");
            case USUARIO:
                return !m.getPermissao().substring(2, 3).equals("0");
        }

        return false;
    }

    public boolean isLoggedIn() {
        return logado;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
