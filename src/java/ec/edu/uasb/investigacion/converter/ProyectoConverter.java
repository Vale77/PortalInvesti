/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.converter;

import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveTipoConvocatoria;
import ec.edu.uasb.investigacion.session.InveProgramaProyectoFacade;
import ec.edu.uasb.investigacion.session.InveProgramaProyectoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveProyectoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveTipoConvocatoriaFacadeLocal;
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
@FacesConverter("proyectoConverter")
public class ProyectoConverter implements Converter {

    InveProyectoFacadeLocal inveProyectoFacadeLocal = lookupInveProyectoFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return inveProyectoFacadeLocal.find(new Long(value));
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
            InveProyecto c = (InveProyecto) value;
            if (c.getPryCodigo() != null) {
                return c.getPryCodigo().toString();
            }
        }
        return null;
    }

    private InveProyectoFacadeLocal lookupInveProyectoFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (InveProyectoFacadeLocal) c.lookup("java:global/PortalInvest/InveProyectoFacade!ec.edu.uasb.investigacion.session.InveProyectoFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
