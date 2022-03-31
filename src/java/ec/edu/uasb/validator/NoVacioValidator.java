/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author victor.barba
 */
@FacesValidator("ec.edu.uasb.validator.NoVacioValidator")
public class NoVacioValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String texto = (String) value;

        String td = (String) component.getAttributes().get("campo");

        if (texto != null && texto.trim().length() == 0) {
            FacesMessage message = new FacesMessage(" No se admite textos en blanco" + (td != null ? " en "+td : ""), " No se admite textos en blanco");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

}
