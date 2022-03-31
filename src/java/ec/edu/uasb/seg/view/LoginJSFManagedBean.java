/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.seg.view;

import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.seg.session.UsuarioFacadeLocal;
import ec.edu.uasb.utils.JsfUtil;
import ec.edu.uasb.utils.LDAPAuthentication;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.naming.AuthenticationException;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@RequestScoped
@Named("loginMgmtBean")
public class LoginJSFManagedBean implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private String username;
    private String password;
    private String perfilLogin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerfilLogin() {
        return perfilLogin;
    }

    public void setPerfilLogin(String perfilLogin) {
        this.perfilLogin = perfilLogin;
    }

    public LoginJSFManagedBean() {
    }

    private String filtrarAreas(List<Perfil> perfiles) {
        for (Perfil pf : perfiles) {
            if (pf.getIdPerfil().equals("USU_SIST")) {//  comparar con USU_SIST  
                return "T";
            } else {
                return "A";
            }
        }
        return null;
    }

    public String autenticar() {
        List<String> msgs = new ArrayList();
        try {
            LDAPAuthentication lDAPAuthentication = new LDAPAuthentication();
            lDAPAuthentication.authenticateLDAPUser(getUsername(), getPassword(), "uasb.edu.ec", "172.16.21.43:389", "dc=uasb,dc=edu,dc=ec");
        } catch (Exception ex) {
            msgs.add(ex.getCause().getMessage());
            msgs.add("Verifique si su teclado esta en Mayúsculas ó Minúsculas");
            JsfUtil.addErrorMessages(null, msgs);
            return null;
        }
//        Usuario user = usuarioFacade.getPerson("wilson.araque");
        Usuario user = usuarioFacade.getPerson(getUsername());
        if (user != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
            return "/loginPerfil";
        } else {
            // buscar en otra base: GRADUADOS y generar: user
            msgs.add("Usuario NO registrado en Base de Datos");
            msgs.add("Verifique si su teclado esta en Mayúsculas ó Minúsculas");
            JsfUtil.addErrorMessages(null, msgs);
        }

        return null;
    }

    public String loginPerfilUsr(String subsist) {
        List<Perfil> perfiles = new ArrayList<>();
        List<String> msgs = new ArrayList();
        FacesContext fc = FacesContext.getCurrentInstance();
        Usuario user = (Usuario) fc.getExternalContext().getSessionMap().get("user");
        if (user != null) {
            try {
                String prfs = JsfUtil.getPerfilPermitido();
                if (perfilLogin.equals("F")) {
                    perfiles = usuarioFacade.getPerfil(user.getUsuCodigo(), prfs);
                } else {
                    // check para permitir accesar al sistema a funcionarios como investigadores
                    perfiles.add(new Perfil(new Long("0"), "USU_INVE", "Usuario investigador"));

                }
                if (!perfiles.isEmpty()) {
                    String filtrarAreas = filtrarAreas(perfiles);
                    fc.getExternalContext().getSessionMap().put("logined", true);
                    fc.getExternalContext().getSessionMap().put("subsist", subsist);
                    fc.getExternalContext().getSessionMap().put("perfiles", perfiles);
                    fc.getExternalContext().getSessionMap().put("filtrarAreas", filtrarAreas);
                    fc.getExternalContext().getFlash().setKeepMessages(true);
                    JsfUtil.addSuccessMessage(null, "BIENVENIDO(A)");
                    if (perfilLogin.equals("F")) {
                        return "pages/principal?faces-redirect=true";
                    } else {
                        return "pages/opciones/registro/registroProyecto?faces-redirect=true";
                    }
                } else {
                    msgs.add("No tiene un perfil asignado. Por favor, contacte a su administrador.");
                }
            } catch (Exception e) {
                msgs.add("No puedo obtener un perfil. " + e.getMessage() + " Por favor, informe a su administrador.");
            } finally {
                JsfUtil.addErrorMessages(null, msgs);
                RequestContext.getCurrentInstance().update("formLogin");
            }
            perfiles = null;
            return null;
        }
        return "/login";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
//        return "/login";
    }

    public void isSessionValid() {

        try {
            final FacesContext context = FacesContext.getCurrentInstance();
//            final HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            final HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
            session.invalidate();
        } catch (Exception e) {
            System.out.println("");
        }
    }
}
