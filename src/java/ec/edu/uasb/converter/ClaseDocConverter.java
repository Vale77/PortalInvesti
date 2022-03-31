/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.converter;

import ec.edu.uasb.principal.entities.PrinClaseDocumento;
import ec.edu.uasb.principal.session.PrinClaseDocumentoFacadeLocal;
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
@FacesConverter("claseDocConverter")

public class ClaseDocConverter implements Converter {

    PrinClaseDocumentoFacadeLocal prinClaseDocumentoFacade = lookupPrinClaseDocumentoFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return prinClaseDocumentoFacade.find(new Short(value));
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
            PrinClaseDocumento c = (PrinClaseDocumento) value;
            if (c.getClsCodigo() != null) {
                return c.getClsCodigo().toString();
            }
        }
        return null;
    }

    private PrinClaseDocumentoFacadeLocal lookupPrinClaseDocumentoFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PrinClaseDocumentoFacadeLocal) c.lookup("java:global/PortalInvest/PrinClaseDocumentoFacade!ec.edu.uasb.principal.session.PrinClaseDocumentoFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
