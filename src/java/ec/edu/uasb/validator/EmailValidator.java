package ec.edu.uasb.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Pavel
 */
@FacesValidator("ec.edu.uasb.validator.EmaiValidator")
public class EmailValidator implements Validator {

//    private static final String EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
//    private static final String EMAIL = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@ [A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String EMAIL = "^[-!#$%&'*+/0-9=?A-Z^_a-z{|}~](\\.?[-!#$%&'*+/0-9=?A-Z^_a-z{|}~])*@[a-zA-Z](-?[a-zA-Z0-9])*(\\.[a-zA-Z](-?[a-zA-Z0-9])*)+$";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Pattern patron = Pattern.compile(EMAIL);
        String email = (String) value;
        if (email != null && !email.isEmpty()) {
            Matcher encaja = patron.matcher(email.trim());
            if (!encaja.matches()) {
                FacesMessage message = new FacesMessage();
                message.setDetail("formato de correo no valido. Ejm. pedroluis@hotmail.com");
                message.setSummary("formato de correo no valido. Ejm. pedroluis@hotmail.com");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }

//            if (isAddressValid(email) == false) {
//                FacesMessage message = new FacesMessage(" Dirección de Correo Electrónico no valida.", " Dirección de Correo Electrónico no valida.");
//                message.setSeverity(FacesMessage.SEVERITY_ERROR);
//                throw new ValidatorException(message);
//            }


//            ValidateEmailRequest messagePart = new ValidateEmailRequest();
//            messagePart.setEmail(email);
//            ValidateEmailResponse result = validateEmail(messagePart);
//            System.out.println(result.getStatus());
//            ReturnValues verifyEmail = verifyEmail(email, "0");
//            boolean goodEmail = verifyEmail.isGoodEmail();
//            if (goodEmail == false) {
//            if (result.getStatus() == false) {
//                FacesMessage message = new FacesMessage(" Dirección de Correo Electrónico no valida.", " Dirección de Correo Electrónico no valida.");
//                message.setSeverity(FacesMessage.SEVERITY_ERROR);
//                throw new ValidatorException(message);
//            }
        }
    }
}
