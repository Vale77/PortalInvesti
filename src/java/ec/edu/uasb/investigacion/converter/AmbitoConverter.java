/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.converter;

import ec.edu.uasb.investigacion.entities.InveAmbitoGeografico;
import ec.edu.uasb.investigacion.session.InveAmbitoGeograficoFacadeLocal;
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
@FacesConverter("ambitoConverter")
public class AmbitoConverter implements Converter {

    InveAmbitoGeograficoFacadeLocal ambitoGeograficoFacadeLocal = lookupInveAmbitoGeograficoFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return ambitoGeograficoFacadeLocal.find(new Short(value));
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
            InveAmbitoGeografico c = (InveAmbitoGeografico) value;
            if (c.getAmbCodigo() != null) {
                return c.getAmbCodigo().toString();
            }
        }
        return null;
    }

    private InveAmbitoGeograficoFacadeLocal lookupInveAmbitoGeograficoFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (InveAmbitoGeograficoFacadeLocal) c.lookup("java:global/PortalInvest/InveAmbitoGeograficoFacade!ec.edu.uasb.investigacion.session.InveAmbitoGeograficoFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
