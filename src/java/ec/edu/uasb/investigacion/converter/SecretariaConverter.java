/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.converter;

import ec.edu.uasb.external.entities.SecretariaArea;
import ec.edu.uasb.external.session.SecretariaAreaFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author victor.barba
 */
@FacesConverter("secretariaConverter")
public class SecretariaConverter implements Converter {

    SecretariaAreaFacadeLocal secretariaAreaFacadeLocal = lookupSecretariaAreaFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return secretariaAreaFacadeLocal.find(new Long(value));
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            SecretariaArea c = (SecretariaArea) value;
            if (c.getPerCodigo() != null) {
                return c.getPerCodigo().toString();
            }
        }
        return null;
    }

    private SecretariaAreaFacadeLocal lookupSecretariaAreaFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (SecretariaAreaFacadeLocal) c.lookup("java:global/PortalInvest/SecretariaAreaFacade!ec.edu.uasb.external.session.SecretariaAreaFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
