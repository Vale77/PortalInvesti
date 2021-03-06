/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.opcion.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.principal.session.PrinPersonaFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.inputtext.InputText;

/**
 *
 * @author victor.barba
 */
@Named(value = "preFormBean")
@ViewScoped
public class PreFormJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private PrinPersonaFacadeLocal prinPersonaFacade;

    private String tipoDoc = null;
    private String nroDocumento;
    private String captcha;
    private InputText inputTextCaptcha = new InputText();
    private PrinPersona persona;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public InputText getInputTextCaptcha() {
        return inputTextCaptcha;
    }

    public void setInputTextCaptcha(InputText inputTextCaptcha) {
        this.inputTextCaptcha = inputTextCaptcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
//</editor-fold>

    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public PreFormJSFManagedBean() {
        super.setPantallaEdicionAdic("/preFormulario.xhtml");
        super.setRenderEdicAdic(true);
        super.setTituloEdicAdic("Investigador");
        super.setVisiblePantallaEdicAdic(true);
        super.setBotonAdicGrabar("Continuar");
    }

    @Override
    public void init() {
        reset();
        retrieve();
    }

    public void retrieve() {
    }

    // <editor-fold defaultstate="collapsed" desc="Validaciones">
    public void validarDocumento(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String id = (String) value;
        if (tipoDoc.equals("C")) {
            if (!id.matches("^\\d+$")) {
                FacesMessage msg = new FacesMessage(" La C??dula debe ser de n??meros", " La C??dula debe contener s??lo de d??gitos");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
            if (id.length() < 10) {
                FacesMessage msg = new FacesMessage(" C??dula debe tener 10 d??gitos", " C??dula debe tener 10 d??gitos");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
            if (!esCedulaValida(id)) {
                FacesMessage msg = new FacesMessage(" Su nro. de C??dula no es v??lido.", " Su nro. de C??dula no es v??lido.");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        } else if (tipoDoc.equals("P")) {
            if (!id.matches("^[a-zA-Z0-9]*$")) {
                FacesMessage msg = new FacesMessage(" En Pasaporte solo permite letras y n??meros", " En Pasaporte solo permite letras y n??meros");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }
    }

    public boolean esCedulaValida(String cedula) {

        int nroProvincias = 24;
        //verifica que tenga 10 d????gitos y que contenga solo valores num????ricos
        if (!((cedula.length() == 10) && cedula.matches("^[0-9]{10}$"))) {
            return false;
        }
        //verifica que los dos primeros d????gitos correspondan a un valor entre 1 y NUMERO_DE_PROVINCIAS
        int prov = Integer.parseInt(cedula.substring(0, 2));

        if (!((prov > 0) && (prov <= nroProvincias))) {
            return false;
        }
        //verifica que el ????ltimo d????gito de la c????dula sea v????lido
        int[] d = new int[10];
        //Asignamos el string a un array
        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.parseInt(cedula.charAt(i) + "");
        }

        int imp = 0;
        int par = 0;

        //sumamos los duplos de posici????n impar
        for (int i = 0; i < d.length; i += 2) {
            d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
            imp += d[i];
        }

        //sumamos los digitos de posici????n par
        for (int i = 1; i < (d.length - 1); i += 2) {
            par += d[i];
        }

        //Sumamos los dos resultados
        int suma = imp + par;

        //Restamos de la decena superior
        int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1) + "0") - suma;

        //Si es diez el d????cimo d????gito es cero
        d10 = (d10 == 10) ? 0 : d10;

        //si el d????cimo d????gito calculado es igual al digitado la c????dula es recta
        return d10 == d[9];
    }

    public void generaCaptcha_processAction() {
        Random rdm = new Random();
        int rl = rdm.nextInt(99999);
        captcha = Integer.toHexString(rl).toUpperCase();
        inputTextCaptcha.resetValue();
    }

    public boolean validarCaptcha() {
        if (!inputTextCaptcha.getValue().toString().trim().equalsIgnoreCase(captcha.trim())) {
            String msg2 = " Los caracteres ingresados no corresponden a los de la imagen.";
            FacesMessage msg = new FacesMessage(msg2, msg2);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("formRegistro:inputTextCaptcha", msg);
            return false;
        }
        return true;
    }
//</editor-fold>

    private void buscarRegistro(String cedPass) {
        persona = prinPersonaFacade.findByCedula(cedPass);
    }

    public String txt_NroDocValueChange() {
        buscarRegistro(nroDocumento);
        return null;
    }

    @Override
    public void resetAdic() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(origRequest.getContextPath() + "/pages/principal.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FormularioJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateAdicButton_processAction(ActionEvent ae) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(origRequest.getContextPath() + "/formulario.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FormularioJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
//        super.updateAdicButton_processAction(ae); //To change body of generated methods, choose Tools | Templates.
    }
}
