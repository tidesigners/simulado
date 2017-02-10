package br.com.tiupgrade.simulado.infra;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named("entityConverter")
public class EntityConverter implements Converter {

	/* Necessita ter um hashcode bem definido para funcionar */

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        return uic.getAttributes().get(string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String objAsString = null;

        if (o != null) {

            objAsString = Integer.toString(System.identityHashCode(o));
            uic.getAttributes().put(objAsString, o);

        }

        return objAsString;
    }

}
