/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.converter;

import ec.edu.uasb.principal.entities.PrinPais;
import ec.edu.uasb.principal.session.PrinPaisFacadeLocal;
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
@FacesConverter("paisConverter")
public class PaisConverter implements Converter {

    PrinPaisFacadeLocal prinPaisFacadeLocal = lookupPrinPaisFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return prinPaisFacadeLocal.find(new Short(value));
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
            PrinPais c = (PrinPais) value;
            if (c.getPaiCodigo() != null) {
                return c.getPaiCodigo().toString();
            }
        }
        return null;
    }

    private PrinPaisFacadeLocal lookupPrinPaisFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PrinPaisFacadeLocal) c.lookup("java:global/PortalInvest/PrinPaisFacade!ec.edu.uasb.principal.session.PrinPaisFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
