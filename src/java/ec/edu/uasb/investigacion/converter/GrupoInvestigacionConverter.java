/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.converter;

import ec.edu.uasb.investigacion.entities.InveGrupoInvestigacion;
import ec.edu.uasb.investigacion.session.InveGrupoInvestigacionFacadeLocal;
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
@FacesConverter("ginConverter")
public class GrupoInvestigacionConverter implements Converter {

    InveGrupoInvestigacionFacadeLocal grupoInvestigacionFacadeLocal = lookupInveGrupoInvestigacionFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return grupoInvestigacionFacadeLocal.find(new Short(value));
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
            InveGrupoInvestigacion c = (InveGrupoInvestigacion) value;
            if (c.getGinCodigo() != null) {
                return c.getGinCodigo().toString();
            }
        }
        return null;
    }

    private InveGrupoInvestigacionFacadeLocal lookupInveGrupoInvestigacionFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (InveGrupoInvestigacionFacadeLocal) c.lookup("java:global/PortalInvest/InveGrupoInvestigacionFacade!ec.edu.uasb.investigacion.session.InveGrupoInvestigacionFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
