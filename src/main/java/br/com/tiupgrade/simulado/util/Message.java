package br.com.tiupgrade.simulado.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class Message {

    public static void send(FacesMessage.Severity severity, String mensagem, String detalhe){
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,
                mensagem,detalhe);
        FacesContext.getCurrentInstance().addMessage(null, m);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    public static void send(String mensagem){
        send(FacesMessage.SEVERITY_INFO, mensagem,"");
    }
}
