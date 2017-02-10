package br.com.tiupgrade.simulado.api;

import br.com.tiupgrade.simulado.util.UrlReader;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import java.util.Calendar;

@Path("consulta")
@Consumes("application/json")
@ApplicationScoped
@Named("consulta")
public class Consulta {

    private JSONObject dados;
    private JSONObject dadosIncidentes;
    private JSONObject dadosDemandasEatividades;

    @GET
    @Path("/atualizar")
    public String atualizar() {
        try {
            JSONObject jsonObj = new JSONObject(UrlReader.getHTML("http://geoportal.cor.rio.gov.br/webservices/API.cfm?chave=140443647687a75b5a96cedd840b7b4b&metodo=palantir"));
            dados = new JSONObject();
            dados.put("timestamp", Calendar.getInstance().getTimeInMillis());
            if (!jsonObj.isNull("mensageria")) {
                dados.put("instalacoes", jsonObj.get("mensageria"));
            } else {
                dados.put("instalacoes", new JSONArray("[]"));
            }
            if (!jsonObj.isNull("demandas")) {
                dados.put("demandas", jsonObj.get("demandas"));
            } else {
                dados.put("demandas", new JSONArray("[]"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            JSONObject jsonObjIncidentes = new JSONObject(UrlReader.getHTML("http://geoportal.cor.rio.gov.br/webservices/API_palantir2.cfm"));
            dadosIncidentes = new JSONObject();
            dadosIncidentes.put("timestamp", Calendar.getInstance().getTimeInMillis());
            if (!jsonObjIncidentes.isNull("incidentes")){
                dadosIncidentes.put("incidentes", jsonObjIncidentes.get("incidentes"));
            }else {
                dadosIncidentes.put("incidentes", new JSONArray("[]"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            JSONObject jsonObjDemandasEatividades = new JSONObject(UrlReader.getHTML("http://geoportal.cor.rio.gov.br/webservices/API_palantir3.cfm"));
            dadosDemandasEatividades = new JSONObject();
            dadosDemandasEatividades.put("timestamp", Calendar.getInstance().getTimeInMillis());
            if (!jsonObjDemandasEatividades.isNull("atividades")) {
                dadosDemandasEatividades.put("atividades", jsonObjDemandasEatividades.get("atividades"));
            } else {
                dadosDemandasEatividades.put("atividades", new JSONArray("[]"));
            }
            if (!jsonObjDemandasEatividades.isNull("demandas")) {
                dadosDemandasEatividades.put("demandas", jsonObjDemandasEatividades.get("demandas"));
            } else {
                dadosDemandasEatividades.put("demandas", new JSONArray("[]"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "";
    }

    public JSONObject getDados() {
        return dados;
    }

    public void setDados(JSONObject dados) {
        this.dados = dados;
    }

    public JSONObject getDadosIncidentes() {
        return dadosIncidentes;
    }

    public void setDadosIncidentes(JSONObject dadosIncidentes) {
        this.dadosIncidentes = dadosIncidentes;
    }

    public JSONObject getDadosDemandasEatividades() {
        return dadosDemandasEatividades;
    }

    public void setDadosDemandasEatividades(JSONObject dadosDemandasEatividades) {
        this.dadosDemandasEatividades = dadosDemandasEatividades;
    }
}
