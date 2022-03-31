/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.converter;

import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.session.InveConvocatoriaFacadeLocal;
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
@FacesConverter("convocatoriaConverter")
public class ConvocatoriaConverter implements Converter {

    InveConvocatoriaFacadeLocal convocatoriaFacadeLocal = lookupInveConvocatoriaFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return convocatoriaFacadeLocal.find(new Long(value));
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
            InveConvocatoria c = (InveConvocatoria) value;
            if (c.getCvoCodigo() != null) {
                return c.getCvoCodigo().toString();
            }
        }
        return null;
    }

    private InveConvocatoriaFacadeLocal lookupInveConvocatoriaFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (InveConvocatoriaFacadeLocal) c.lookup("java:global/PortalInvest/InveConvocatoriaFacade!ec.edu.uasb.investigacion.session.InveConvocatoriaFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
