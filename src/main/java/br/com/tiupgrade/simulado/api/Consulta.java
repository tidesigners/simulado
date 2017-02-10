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


    @GET
    @Path("/atualizar")
    public String atualizar() {

        return "";
    }


}
