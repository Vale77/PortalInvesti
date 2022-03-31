/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.validator;

import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.session.InveProyectoFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author victor.barba
 */
@FacesValidator("ec.edu.uasb.investigacion.validator.CodProyValidator")
public class CodProyValidator implements Validator {

    InveProyectoFacadeLocal inveProyectoFacade = lookupInveProyectoFacadeLocal();

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String codigo = (String) value;

        InveProyecto pry = (InveProyecto) component.getAttributes().get("proyecto");
        
        if (pry.getPryCodigoCi() != null && pry.getPryCodigoCi().equals(codigo)) {
            return;
        }

        pry = inveProyectoFacade.getProyectoBy(codigo.trim());
        if (pry != null) {

            FacesMessage msg = new FacesMessage(" El Código ingresado ya existe", " El Código ingresado ya existe");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
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
