package br.com.tiupgrade.simulado.dominio;

public enum TipoPerfilEnum {

    ADMINISTRADOR("Administrador"),
    OBSERVADOR("Observador"),
    GESTOR("Gestor");

    private final String perfil;

    private TipoPerfilEnum(String perfil) {
        this.perfil = perfil;
    }

    public String getPerfil() {
        return this.perfil;
    }

}
