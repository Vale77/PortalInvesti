/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.converter;

import ec.edu.uasb.investigacion.entities.InveTipoProyecto;
import ec.edu.uasb.investigacion.session.InveTipoProyectoFacadeLocal;
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
@FacesConverter("tipoProyConverter")
public class TipoProyectoConverter implements Converter {

    InveTipoProyectoFacadeLocal inveTipoProyectoFacadeLocal = lookupInveTipoProyectoFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return inveTipoProyectoFacadeLocal.find(new Short(value));
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
            InveTipoProyecto c = (InveTipoProyecto) value;
            if (c.getTipCodigo() != null) {
                return c.getTipCodigo().toString();
            }
        }
        return null;
    }

    private InveTipoProyectoFacadeLocal lookupInveTipoProyectoFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (InveTipoProyectoFacadeLocal) c.lookup("java:global/PortalInvest/InveTipoProyectoFacade!ec.edu.uasb.investigacion.session.InveTipoProyectoFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
