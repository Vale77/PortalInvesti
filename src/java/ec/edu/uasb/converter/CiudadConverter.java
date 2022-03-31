/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.converter;

import ec.edu.uasb.principal.entities.PrinCiudad;
import ec.edu.uasb.principal.entities.PrinCiudadPK;
import ec.edu.uasb.principal.entities.PrinPais;
import ec.edu.uasb.principal.session.PrinCiudadFacadeLocal;
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
@FacesConverter("ciudadConverter")
public class CiudadConverter implements Converter {

    PrinCiudadFacadeLocal prinCiudadFacadeLocal = lookupPrinCiudadFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        PrinPais pais = (PrinPais) component.getAttributes().get("pais");
        if (pais == null) {
            return null;
        }
//                System.out.println("getAsObject "+pais+" value "+value);
        if (value != null && value.trim().length() > 0) {
            try {
                return prinCiudadFacadeLocal.find(new PrinCiudadPK(pais.getPaiCodigo(), new Integer(value)));
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        System.out.println("getAsString "+value);
        if (value != null) {
            PrinCiudad c = (PrinCiudad) value;
            if (c.getPrinCiudadPK().getCiuCodigo() != null) {
                return c.getPrinCiudadPK().getCiuCodigo().toString();
            }
        }
        return null;
    }

    private PrinCiudadFacadeLocal lookupPrinCiudadFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PrinCiudadFacadeLocal) c.lookup("java:global/PortalInvest/PrinCiudadFacade!ec.edu.uasb.principal.session.PrinCiudadFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
