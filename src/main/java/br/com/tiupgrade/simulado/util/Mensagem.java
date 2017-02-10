package br.com.tiupgrade.simulado.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Mensagem {

    public static void enviar(String msg, String detalhe, Severity tipo, String componente) {
        FacesContext.getCurrentInstance().addMessage(componente, new FacesMessage(tipo, msg, detalhe));
    }

    public static void enviar(String msg, String detalhe, Severity tipo) {
        enviar(msg, detalhe, FacesMessage.SEVERITY_INFO, null);
    }

    public static void enviar(String msg, String detalhe) {
        enviar(msg, detalhe, FacesMessage.SEVERITY_INFO);
    }

    public static void enviar(String msg) {
        enviar(msg, "", FacesMessage.SEVERITY_INFO);
    }

    public static void enviar(String msg, Severity tipo) {
        enviar(msg, "", tipo);
    }

}
