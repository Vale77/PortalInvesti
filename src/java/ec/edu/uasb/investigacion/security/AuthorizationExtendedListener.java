/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.security;

import ec.edu.uasb.security.AuthorizationListener;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpSession;

/**
 *
 * @author victor.barba
 */
public class AuthorizationExtendedListener extends AuthorizationListener{

    private final String loginPage = "/login";
    private final String errorPage = "/error/";
    private final String loginPerfilPage = "/loginPerfil";

    private boolean isSessionControlRequiredForThisResource(String currentPage) {
        //only when request target is NOT welcome or timeout
        boolean controlRequired = (currentPage.contains(loginPage)
                || currentPage.contains(errorPage)
                || currentPage.contains(loginPerfilPage)
                || currentPage.contains("javax.faces.resource"));

        return controlRequired;
    }

    @Override
    public void afterPhase(PhaseEvent pe) {
        FacesContext facesContext = pe.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();
//
//        boolean isLoginPage = currentPage.endsWith(loginPage);
//        boolean targetIsErrorPage = currentPage.contains(errorPage);

        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Object currentUser = session.getAttribute("logined");

        boolean newSession = (session == null) || (session.isNew());
        boolean postback = !facesContext.getExternalContext().getRequestParameterMap().isEmpty();
        boolean timedout = postback && newSession;

//        System.out.println("timedout " + timedout + "  currentPage " + currentPage + " currentUser " + currentUser);
        boolean isEventosPage = currentPage.contains(loginPerfilPage);
        if (timedout && !isEventosPage) {
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
            nh.handleNavigation(facesContext, null, "sessionExpired");
        } else {
            boolean controlRequired = isSessionControlRequiredForThisResource(currentPage);
            if (!controlRequired && (currentUser == null || currentUser == "")) {
                NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
                nh.handleNavigation(facesContext, null, "sinAcceso");
            }
//            if (!isLoginPage && !targetIsErrorPage && (currentUser == null || currentUser == "")) {
//                NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
//                nh.handleNavigation(facesContext, null, "sinAcceso");
//            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
//        FacesContext facesContext = pe.getFacesContext();
//        if (facesContext.getViewRoot() == null) {
//            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
//            nh.handleNavigation(facesContext, null, "/login");
//        }

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
