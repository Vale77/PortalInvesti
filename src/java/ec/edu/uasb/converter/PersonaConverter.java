/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.converter;

import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.principal.session.PrinPersonaFacadeLocal;
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
@FacesConverter("personaConverter")
public class PersonaConverter implements Converter {

    PrinPersonaFacadeLocal prinPersonaFacadeLocal = lookupPrinPersonaFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return prinPersonaFacadeLocal.find(new Long(value));
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
            PrinPersona c = (PrinPersona) value;
            if (c.getPerCodigo() != null) {
                return c.getPerCodigo().toString();
            }
        }
        return null;
    }

    private PrinPersonaFacadeLocal lookupPrinPersonaFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PrinPersonaFacadeLocal) c.lookup("java:global/PortalInvest/PrinPersonaFacade!ec.edu.uasb.principal.session.PrinPersonaFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
