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
@FacesValidator("ec.edu.uasb.validator.CedulaValidator")
public class CedulaValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String id = (String) value;

        Object td = component.getAttributes().get("tipoDocumento");
        if (td == null) {
            return;
        }
        String tipoDocumento = (String) td;
        if (tipoDocumento.equals("C")) {
            if (!id.matches("^\\d+$")) {
                FacesMessage msg = new FacesMessage(" La Cédula debe ser de números", " La Cédula debe contener sólo de dígitos");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
            if (id.length() < 10) {
                FacesMessage msg = new FacesMessage(" Cédula debe tener 10 dígitos", " Cédula debe tener 10 dígitos");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
            if (!esCedulaValida(id)) {
                FacesMessage msg = new FacesMessage(" Su nro. de Cédula no es válido.", " Su nro. de Cédula no es válido.");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        } else if (tipoDocumento.equals("P")) {
//            if (!id.matches("^[a-zA-Z0-9]*$")) {
//                FacesMessage msg = new FacesMessage(" En Pasaporte solo permite letras y números", " En Pasaporte solo permite letras y números");
//                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//                throw new ValidatorException(msg);
//            }
        }
    }

    public boolean esCedulaValida(String cedula) {

        int nroProvincias = 24;
        //verifica que tenga 10 dÃ­gitos y que contenga solo valores numÃ©ricos
        if (!((cedula.length() == 10) && cedula.matches("^[0-9]{10}$"))) {
            return false;
        }
        //verifica que los dos primeros dÃ­gitos correspondan a un valor entre 1 y NUMERO_DE_PROVINCIAS
        int prov = Integer.parseInt(cedula.substring(0, 2));

        if (!((prov > 0) && (prov <= nroProvincias))) {
            return false;
        }
        //verifica que el Ãºltimo dÃ­gito de la cÃ©dula sea vÃ¡lido
        int[] d = new int[10];
        //Asignamos el string a un array
        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.parseInt(cedula.charAt(i) + "");
        }

        int imp = 0;
        int par = 0;

        //sumamos los duplos de posiciÃ³n impar
        for (int i = 0; i < d.length; i += 2) {
            d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
            imp += d[i];
        }

        //sumamos los digitos de posiciÃ³n par
        for (int i = 1; i < (d.length - 1); i += 2) {
            par += d[i];
        }

        //Sumamos los dos resultados
        int suma = imp + par;

        //Restamos de la decena superior
        int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1) + "0") - suma;

        //Si es diez el dÃ©cimo dÃ­gito es cero
        d10 = (d10 == 10) ? 0 : d10;

        //si el dÃ©cimo dÃ­gito calculado es igual al digitado la cÃ©dula es recta
        return d10 == d[9];
    }

}
