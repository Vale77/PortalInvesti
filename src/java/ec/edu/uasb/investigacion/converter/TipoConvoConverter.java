/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.converter;

import ec.edu.uasb.investigacion.entities.InveTipoConvocatoria;
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
@FacesConverter("tipoConvoConverter")
public class TipoConvoConverter implements Converter {

    InveTipoConvocatoriaFacadeLocal inveTipoConvocatoriaFacadeLocal = lookupInveTipoConvocatoriaFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return inveTipoConvocatoriaFacadeLocal.find(new Short(value));
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
            InveTipoConvocatoria c = (InveTipoConvocatoria) value;
            if (c.getTcvCodigo() != null) {
                return c.getTcvCodigo().toString();
            }
        }
        return null;
    }

    private InveTipoConvocatoriaFacadeLocal lookupInveTipoConvocatoriaFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (InveTipoConvocatoriaFacadeLocal) c.lookup("java:global/PortalInvest/InveTipoConvocatoriaFacade!ec.edu.uasb.investigacion.session.InveTipoConvocatoriaFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
