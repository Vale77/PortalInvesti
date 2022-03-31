/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.converter;

import ec.edu.uasb.investigacion.entities.InveEstadoValoracion;
import ec.edu.uasb.investigacion.session.InveEstadoValoracionFacadeLocal;
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
@FacesConverter("estadoValConverter")
public class EstadoValConverter implements Converter {

    InveEstadoValoracionFacadeLocal estadoValoracionFacadeLocal = lookupInveEstadoValoracionFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            return estadoValoracionFacadeLocal.find(value);
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value != null) {
            InveEstadoValoracion c = (InveEstadoValoracion) value;
            if (c.getEsvCodigo() != null) {
                return c.getEsvCodigo();
            }
        }
        return null;
    }

    private InveEstadoValoracionFacadeLocal lookupInveEstadoValoracionFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (InveEstadoValoracionFacadeLocal) c.lookup("java:global/PortalInvest/InveEstadoValoracionFacade!ec.edu.uasb.investigacion.session.InveEstadoValoracionFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
