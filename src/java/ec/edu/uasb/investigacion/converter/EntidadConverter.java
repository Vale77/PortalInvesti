/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.converter;

import ec.edu.uasb.principal.entities.PrinEntidad;
import ec.edu.uasb.principal.session.PrinEntidadFacadeLocal;
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
@FacesConverter("entidadConverter")
public class EntidadConverter implements Converter {

    PrinEntidadFacadeLocal entidadFacadeLocal = lookupPrinEntidadFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return entidadFacadeLocal.find(new Short(value));
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
            PrinEntidad c = (PrinEntidad) value;
            if (c.getEntCodigo() != null) {
                return c.getEntCodigo().toString();
            }
        }
        return null;
    }

    private PrinEntidadFacadeLocal lookupPrinEntidadFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PrinEntidadFacadeLocal) c.lookup("java:global/PortalInvest/PrinEntidadFacade!ec.edu.uasb.principal.session.PrinEntidadFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
