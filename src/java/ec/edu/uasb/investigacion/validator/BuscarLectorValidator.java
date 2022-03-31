/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.validator;

import ec.edu.uasb.investigacion.entities.InveLectorContratado;
import ec.edu.uasb.investigacion.session.InveLectorContratadoFacadeLocal;
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
@FacesValidator("ec.edu.uasb.investigacion.validator.buscarLectorValidator")
public class BuscarLectorValidator implements Validator {

    InveLectorContratadoFacadeLocal inveLectorContratadoFacade = lookupInveLectorContratadoFacadeLocal();

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String codigo = (String) value;

        InveLectorContratado lectorContratado = inveLectorContratadoFacade.findById(codigo.trim());
        if (lectorContratado != null) {
            FacesMessage msg = new FacesMessage(" La persona con esta identificación ya se encuentra registrada en base de lectores ", " La persona con esta identificación ya se encuentra registrada en base de lectores");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    private InveLectorContratadoFacadeLocal lookupInveLectorContratadoFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (InveLectorContratadoFacadeLocal) c.lookup("java:global/PortalInvest/InveLectorContratadoFacade!ec.edu.uasb.investigacion.session.InveLectorContratadoFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
