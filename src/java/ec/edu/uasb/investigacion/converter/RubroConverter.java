/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.converter;

import ec.edu.uasb.investigacion.entities.InveRol;
import ec.edu.uasb.investigacion.entities.InveRubro;
import ec.edu.uasb.investigacion.session.InveRolFacadeLocal;
import ec.edu.uasb.investigacion.session.InveRubroFacadeLocal;
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
@FacesConverter("rubroConverter")
public class RubroConverter implements Converter {

    InveRubroFacadeLocal inveRubroFacadeLocal = lookupInveRubroFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return inveRubroFacadeLocal.find(new Short(value));
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
            InveRubro c = (InveRubro) value;
            if (c.getRubCodigo() != null) {
                return c.getRubCodigo().toString();
            }
        }
        return null;
    }

    private InveRubroFacadeLocal lookupInveRubroFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (InveRubroFacadeLocal) c.lookup("java:global/PortalInvest/InveRubroFacade!ec.edu.uasb.investigacion.session.InveRubroFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
