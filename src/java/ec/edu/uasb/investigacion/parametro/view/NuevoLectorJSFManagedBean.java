/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.parametro.view;

import ec.edu.uasb.exception.PersonaException;
import ec.edu.uasb.investigacion.session.InveLectorContratadoFacadeLocal;
import ec.edu.uasb.investigacion.session.InveLectorFacadeLocal;
import ec.edu.uasb.principal.entities.PrinCiudad;
import ec.edu.uasb.principal.entities.PrinEntidad;
import ec.edu.uasb.principal.entities.PrinPais;
import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.principal.entities.PrinTitulo;
import ec.edu.uasb.principal.session.PrinCiudadFacadeLocal;
import ec.edu.uasb.principal.session.PrinEntidadFacadeLocal;
import ec.edu.uasb.principal.session.PrinPaisFacadeLocal;
import ec.edu.uasb.principal.session.PrinPersonaFacadeLocal;
import ec.edu.uasb.principal.session.PrinTituloFacadeLocal;
import ec.edu.uasb.seg.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@Named(value = "nuevoLectorBean")
@ViewScoped
public class NuevoLectorJSFManagedBean implements Serializable {

    @EJB
    private PrinEntidadFacadeLocal prinEntidadFacade;
    @EJB
    private PrinPersonaFacadeLocal prinPersonaFacade;
    @EJB
    private PrinPaisFacadeLocal prinPaisFacade;
    @EJB
    private PrinCiudadFacadeLocal prinCiudadFacade;
    @EJB
    private PrinTituloFacadeLocal prinTituloFacade;
    @EJB
    private InveLectorContratadoFacadeLocal inveLectorContratadoFacade;

    private final Usuario usr;
    private String tipoDoc = null;
    private String nroDocumento;
    private PrinPersona nuevoLector;
    private PrinPersona personaOrig;
    private List<PrinPais> listaPais = new ArrayList<>();
    private List<PrinCiudad> listaCiudades = new ArrayList<>();
    private List<PrinEntidad> listaUniversidades = new ArrayList<>();
    private PrinPais paisDom;
    private PrinCiudad ciudadDom;
    private PrinTitulo prinTitulo;

    //<editor-fold defaultstate="collapsed" desc="Propiedades">
    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public PrinPersona getNuevoLector() {
        return nuevoLector;
    }

    public void setNuevoLector(PrinPersona nuevoLector) {
        this.nuevoLector = nuevoLector;
    }

    public PrinPersona getPersonaOrig() {
        return personaOrig;
    }

    public List<PrinPais> getListaPais() {
        return listaPais;
    }

    public List<PrinCiudad> getListaCiudades() {
        return listaCiudades;
    }

    public List<PrinEntidad> getListaUniversidades() {
        return listaUniversidades;
    }

    public PrinPais getPaisDom() {
        return paisDom;
    }

    public void setPaisDom(PrinPais paisDom) {
        this.paisDom = paisDom;
    }

    public PrinCiudad getCiudadDom() {
        return ciudadDom;
    }

    public void setCiudadDom(PrinCiudad ciudadDom) {
        this.ciudadDom = ciudadDom;
    }

    public PrinTitulo getPrinTitulo() {
        return prinTitulo;
    }

    public void setPrinTitulo(PrinTitulo prinTitulo) {
        this.prinTitulo = prinTitulo;
    }
//</editor-fold>
    
    /**
     * Creates a new instance of NuevoLectorJSFManagedBean
     */
    public NuevoLectorJSFManagedBean() {
                this.usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    }

    @PostConstruct
    public void init() {
        listaUniversidades = prinEntidadFacade.findAllUniversidades();
        listaPais = prinPaisFacade.findAllorde();
            ciudadDom = null;
    }

    public void cargarCiudades() {
        listaCiudades.clear();
        if (paisDom != null) {
            ciudadDom = null;
            listaCiudades = prinCiudadFacade.findAllorde(paisDom.getPaiCodigo());
        }
    }

    private void buscarRegistro(String cedPass) throws PersonaException {
        nuevoLector = prinPersonaFacade.findByCedula(cedPass);
        if (nuevoLector == null) {
            nuevoLector = new PrinPersona();
            nuevoLector.setPerTipoDoc(tipoDoc);
            nuevoLector.setPerIdDoc(nroDocumento);
            prinTitulo = new PrinTitulo();
            prinTitulo.setEsPrincipal("S");
        } else {
            try {
                personaOrig = nuevoLector.clone();
                nroDocumento = personaOrig.getPerIdDoc();
                if (personaOrig.getPrinCiudad() != null) {
                    paisDom = personaOrig.getPrinCiudad().getPrinPais();
                    cargarCiudades();
                    ciudadDom = personaOrig.getPrinCiudad();
                }
                List<PrinTitulo> temp = prinTituloFacade.findTitulosBy(personaOrig);
                if (temp.isEmpty()) {
                    prinTitulo = new PrinTitulo();
                    prinTitulo.setEsPrincipal("S");
                } else {
                    prinTitulo = temp.get(0);
                }

            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String txt_NroDocValueChange() {
        try {
            buscarRegistro(nroDocumento);
            RequestContext.getCurrentInstance().execute(nuevoLector.getPerCodigo() == null ? "setfocoNombre();" : "setfocoEmail();");
        } catch (PersonaException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            Collection paneles = new ArrayList();
            paneles.add("formEditAdic:pnlPerso");
            paneles.add("formEditAdic:panelDomicilio");
            paneles.add("formEditAdic:panelTitulo");
            RequestContext.getCurrentInstance().update(paneles);
        }
        return null;
    }

    public void addNuevoItemButton_processAction() {
        nuevoLector = new PrinPersona();
        prinTitulo = new PrinTitulo();
        paisDom = null;
        ciudadDom = null;
        tipoDoc = null;
        nroDocumento = null;
    }

    public void updateAdicButton_processAction() {
        nuevoLector.setPrinCiudad(ciudadDom);
        nuevoLector.setPerPrimerApellido(nuevoLector.getPerPrimerApellido().trim().toUpperCase());
        nuevoLector.setPerSegundoApellido(nuevoLector.getPerSegundoApellido() != null && !nuevoLector.getPerSegundoApellido().trim().isEmpty() ? nuevoLector.getPerSegundoApellido().trim().toUpperCase() : null);
        nuevoLector.setPerNombres(nuevoLector.getPerNombres().trim().toUpperCase());
        nuevoLector.setPerUsuario(usr.getUsuCedPass());
        nuevoLector.setPerFechaCrea(new Date());
        inveLectorContratadoFacade.createLectorContratado(nuevoLector, prinTitulo);

    }
}
