/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.converter;


import ec.edu.uasb.investigacion.entities.InveObjetivoSocioEconomico;
import ec.edu.uasb.investigacion.session.InveObjetivoSocioEconomicoFacadeLocal;
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
@FacesConverter("oseConverter")
public class OseConverter implements Converter {

    InveObjetivoSocioEconomicoFacadeLocal socioEconomicoFacadeLocal = lookupInveObjetivoSocioEconomicoFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return socioEconomicoFacadeLocal.find(new Short(value));
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
            InveObjetivoSocioEconomico c = (InveObjetivoSocioEconomico) value;
            if (c.getOseCodigo() != null) {
                return c.getOseCodigo().toString();
            }
        }
        return null;
    }

    private InveObjetivoSocioEconomicoFacadeLocal lookupInveObjetivoSocioEconomicoFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (InveObjetivoSocioEconomicoFacadeLocal) c.lookup("java:global/PortalInvest/InveObjetivoSocioEconomicoFacade!ec.edu.uasb.investigacion.session.InveObjetivoSocioEconomicoFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
