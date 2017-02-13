package br.com.tiupgrade.simulado.dominio;

public enum TipoModuloEnum {
    /*
    * Permissao A G O
    * A = Administrador
    * G = Gestor
    * U = USUARIO
    *
    * 0 = não pode visualizar
    * 1 = somente visualizar
    * 2 = visualizar e editar
    *
    * Exemplo
    * IMPORTAR("importar","210")
    *    Administrador = 2 (pode ver e editar este módulo),
    *    Gestor = 1 (somente visualizar) e
    *    USUARIO = 0 (Não tem acesso)
    * */
    CONTINGENCIA("contingência", "220"),
    DOCUMENTO("documento", "220"),
    INFORME("informe", "220"),
    IMPORTAR("importar", "200"),
    REGIAO("região", "210"),
    MODAL("modal", "220"),
    INSTALACAO("instalação", "210"),
    USUARIO("usuário", "200"),
    GRAFICO("grafico", "211"),
    CONSULTA("CONSULTA", "211"),
    CONSULTA_RISCO("consulta_risco", "211"),
    CONSULTA_PUBLICO_INSTALACAO("consulta_publico_instalacao", "211"),
    CONSULTA_PUBLICO_MODAL("consulta_publico_modal", "211"),
    CONSULTA_INCIDENTE("consulta_incidente", "211"),
    CONSULTA_ATIVIDADE_DEMANDA("consulta_atividade_demanda", "211"),;

    private final String modulo;
    private final String permissao;

    private TipoModuloEnum(String modulo, String permissao) {
        this.modulo = modulo;
        this.permissao = permissao;
    }

    public String getModulo() {
        return this.modulo;
    }

    public String getPermissao() {
        return this.permissao;
    }

}
