/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.converter;


import ec.edu.uasb.external.entities.Programa;
import ec.edu.uasb.external.session.ProgramaFacadeLocal;
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
 * @author xavier.duque
 */

@FacesConverter("progConverter")

public class ProgramaConverter implements Converter {
    
    ProgramaFacadeLocal progFacadeLocal = lookupProgFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return progFacadeLocal.find(new Long(value));
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
            Programa c = (Programa) value;
            if (c.getPrgCodigo()!= null) {
                return c.getPrgCodigo().toString();
            }
        }
        return null;
    }

    private ProgramaFacadeLocal lookupProgFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (ProgramaFacadeLocal) c.lookup("java:global/PortalInvest/ProgramaFacade!ec.edu.uasb.external.session.ProgramaFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
