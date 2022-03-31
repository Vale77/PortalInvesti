/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.converter;

import ec.edu.uasb.principal.entities.PrinTipoDedicacion;
import ec.edu.uasb.principal.session.PrinTipoDedicacionFacadeLocal;
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
@FacesConverter("tipoDedicacionConverter")
public class TipoDedicacionConverter implements Converter {

    PrinTipoDedicacionFacadeLocal tipoDedicacionFacadeLocal = lookupPrinTipoDedicacionFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return tipoDedicacionFacadeLocal.find(new Short(value));
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
            PrinTipoDedicacion c = (PrinTipoDedicacion) value;
            if (c.getTdeCodigo() != null) {
                return c.getTdeCodigo().toString();
            }
        }
        return null;
    }

    private PrinTipoDedicacionFacadeLocal lookupPrinTipoDedicacionFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PrinTipoDedicacionFacadeLocal) c.lookup("java:global/PortalInvest/PrinTipoDedicacionFacade!ec.edu.uasb.principal.session.PrinTipoDedicacionFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
