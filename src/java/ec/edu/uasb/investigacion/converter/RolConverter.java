/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.converter;

import ec.edu.uasb.investigacion.entities.InveRol;
import ec.edu.uasb.investigacion.session.InveRolFacadeLocal;
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
@FacesConverter("rolConverter")
public class RolConverter implements Converter {

    InveRolFacadeLocal inveRolFacadeLocal = lookupInveRolFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return inveRolFacadeLocal.find(new Short(value));
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
            InveRol c = (InveRol) value;
            if (c.getRolCodigo()!= null) {
                return c.getRolCodigo().toString();
            }
        }
        return null;
    }

    private InveRolFacadeLocal lookupInveRolFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (InveRolFacadeLocal) c.lookup("java:global/PortalInvest/InveRolFacade!ec.edu.uasb.investigacion.session.InveRolFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
