/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.opcion.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.external.entities.AnioAcademico;
import ec.edu.uasb.external.entities.AreaAcademica;
import ec.edu.uasb.external.entities.Programa;
import ec.edu.uasb.external.session.AnioAcademicoFacadeLocal;
import ec.edu.uasb.external.session.AreaAcademicaFacadeLocal;
import ec.edu.uasb.external.session.ProgramaFacadeLocal;
import ec.edu.uasb.investigacion.entities.InveCarta;
import ec.edu.uasb.investigacion.entities.InveLineaInvestProyec;
import ec.edu.uasb.investigacion.entities.InveProgramaProyecto;
import ec.edu.uasb.investigacion.entities.InveProgramaProyectoPK;
import ec.edu.uasb.investigacion.session.InveCartaFacadeLocal;
import ec.edu.uasb.investigacion.session.InveLineaInvestProyecFacadeLocal;
import ec.edu.uasb.investigacion.session.InveProgramaProyectoFacadeLocal;
import ec.edu.uasb.principal.entities.PrinCategoriaInvest;
import ec.edu.uasb.principal.entities.PrinDocumento;
import ec.edu.uasb.principal.session.PrinCategoriaInvestFacadeLocal;
import ec.edu.uasb.principal.session.PrinDocumentoFacadeLocal;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author victor.barba
 */
@Named(value = "cartaBean")
@ViewScoped
public class CartaJSFManagedBean extends BaseJSFManagedBean implements Serializable {

    @EJB
    private PrinCategoriaInvestFacadeLocal prinCategoriaInvestFacade;
    @EJB
    private InveCartaFacadeLocal inveCartaFacade;
    @EJB
    private AnioAcademicoFacadeLocal anioAcademicoFacade;
    @EJB
    private AreaAcademicaFacadeLocal areaAcademicaFacade;
    @EJB
    private ProgramaFacadeLocal programaFacade;
    @EJB
    private InveLineaInvestProyecFacadeLocal inveLineaInvestProyecFacade;
    @EJB
    private InveProgramaProyectoFacadeLocal inveProgramaProyectoFacade;
    @EJB
    private PrinDocumentoFacadeLocal prinDocumentoFacade;

    private final String modulo;
    private final Usuario usr;
    private String filtrarAreas;
    private String perfil = new String();

    private Short anio;
    private Short areaCodigo;
    private List<InveCarta> listaCartas = new ArrayList<>();
    private List<InveCarta> filteredCartas;
    private List<AnioAcademico> listaAnios = new ArrayList<>();
    private List<AreaAcademica> listaAreas = new ArrayList<>();
    private List<Programa> listaProgramas = new ArrayList<>();
    private List<InveProgramaProyecto> listaProgVincul = new ArrayList<>();
    private List<InveLineaInvestProyec> listaLineaInvestProyec = new ArrayList<>();
    private List<PrinCategoriaInvest> listaCateg = new ArrayList<>();
    private InveCarta cartaSelected;
    private InveCarta cartaEdit;
    private InveLineaInvestProyec inveLineaInvestProyec;
    private Integer lipCodigo;
    private Programa programa;
    private InveProgramaProyecto programaProyectoSelected;
    private String botonAdd;
    private PrinDocumento docPry;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public List<InveCarta> getListaCartas() {
        return listaCartas;
    }

    public List<InveCarta> getFilteredCartas() {
        return filteredCartas;
    }

    public void setFilteredCartas(List<InveCarta> filteredCartas) {
        this.filteredCartas = filteredCartas;
    }

    public List<PrinCategoriaInvest> getListaCateg() {
        return listaCateg;
    }

    public List<InveLineaInvestProyec> getListaLineaInvestProyec() {
        return listaLineaInvestProyec;
    }

    public List<AnioAcademico> getListaAnios() {
        return listaAnios;
    }

    public List<AreaAcademica> getListaAreas() {
        return listaAreas;
    }

    public List<InveProgramaProyecto> getListaProgVincul() {
        return listaProgVincul;
    }

    public List<Programa> getListaProgramas() {
        return listaProgramas;
    }

    public InveCarta getCartaSelected() {
        return cartaSelected;
    }

    public void setCartaSelected(InveCarta cartaSelected) {
        this.cartaSelected = cartaSelected;
    }

    public InveCarta getCartaEdit() {
        return cartaEdit;
    }

    public void setCartaEdit(InveCarta cartaEdit) {
        this.cartaEdit = cartaEdit;
    }

    public Short getAnio() {
        return anio;
    }

    public void setAnio(Short anio) {
        this.anio = anio;
    }

    public Short getAreaCodigo() {
        return areaCodigo;
    }

    public void setAreaCodigo(Short areaCodigo) {
        this.areaCodigo = areaCodigo;
    }

    public Integer getLipCodigo() {
        return lipCodigo;
    }

    public void setLipCodigo(Integer lipCodigo) {
        this.lipCodigo = lipCodigo;
    }

    public InveProgramaProyecto getProgramaProyectoSelected() {
        return programaProyectoSelected;
    }

    public void setProgramaProyectoSelected(InveProgramaProyecto programaProyectoSelected) {
        this.programaProyectoSelected = programaProyectoSelected;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public PrinDocumento getDocPry() {
        return docPry;
    }

    public InveLineaInvestProyec getInveLineaInvestProyec() {
        return inveLineaInvestProyec;
    }

    public void setInveLineaInvestProyec(InveLineaInvestProyec inveLineaInvestProyec) {
        this.inveLineaInvestProyec = inveLineaInvestProyec;
    }

    //</editor-fold>
    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public CartaJSFManagedBean() {
        super.setTieneMenuBar(true);
        super.setPosicionEdicion("top");
        super.getListaWidgetPantallas().add("widgetCarta");
        super.setWidthEditDisplay("1048");
        super.setRenderEdicAdic(true);
        // seguridad en perfiles y areas
        this.usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        modulo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("subsist");
        List<Perfil> perfiles = (List<Perfil>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfiles");
        if (perfiles != null) {
            filtrarAreas = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("filtrarInfo");// áreas inicializadas             
            for (Perfil perf : perfiles) {
                if (perf.getIdPerfil().equals("USU_SIST") || perf.getIdPerfil().equals("ADM_INVE") || perf.getIdPerfil().equals("SEC_INVE")) {
                    perfil = perf.getIdPerfil();
                    filtrarAreas = "T";
                    break;
                } else if (perf.getIdPerfil().equals("SEC_INVE") || perf.getIdPerfil().equals("SEC_INS")) {
                    perfil = perf.getIdPerfil();
                    filtrarAreas = "A";
                    break;
                }
            }
        }
    }

    @Override
    public boolean isVisibleDelete() {
        return super.isVisibleDelete(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDisabledDelete() {
        return super.isDisabledDelete(); //To change body of generated methods, choose Tools | Templates.
    }

    private void recuperarAreas() {
//        System.out.println("filtrarAreas " + filtrarAreas);
        if (filtrarAreas != null) {
            if (filtrarAreas.equals("T")) {
                // todas las áreas
                listaAreas = areaAcademicaFacade.getListaAreasDepartamentos();
            } else if (filtrarAreas.equals("A")) {
                // área de secretaria
                listaAreas = areaAcademicaFacade.getAreasByUsuario(usr.getUsuCodigo());
            }
            if (areaCodigo == null) {
                areaCodigo = listaAreas.size() == 1 ? listaAreas.get(0).getAreaCodigo() : null;
            }
        }
    }

    @Override
    public void reset() {
        cartaEdit = null;
        cartaSelected = null;
        super.reset();
    }

    @Override
    public void init() {
        listaAnios = anioAcademicoFacade.getListaAnios();
        listaCateg = prinCategoriaInvestFacade.findAll();
        recuperarAreas();
        retrieveBy("");
        super.init();
    }

    @Override
    public void menuDefault() {
        this.setVisibleNew(false);
        this.setVisibleEdit(true);
        this.setVisibleDelete(false);
        refreshMenuBar(true);
    }

    public void retrieveBy(String op) {
        cartaSelected = null;
        listaCartas.clear();
        if (op.equals("area")) {
            if (areaCodigo != null) {
                listaProgramas = programaFacade.getListaProgramasByAreaAnio(areaCodigo);
                listaLineaInvestProyec = inveLineaInvestProyecFacade.findBy(areaCodigo);
            }
        }
        if (anio != null && areaCodigo != null) {
            listaCartas = inveCartaFacade.findBy(anio, areaCodigo);
        }
         RequestContext.getCurrentInstance().execute("PF('widgetCarta').clearFilters();");
    }
    //<editor-fold defaultstate="collapsed" desc="Boton añadir, eliminar y actualizar de listados">

    public void processAddButton(ActionEvent ae) {
        botonAdd = ae.getComponent().getId();
        this.setBotonAdicGrabar("Añadir");
        switch (botonAdd) {
            case ("commandButtonProg"):
                this.setPantallaEdicionAdic("/pages/opciones/carta/editProgVincu.xhtml");
                this.setTituloEdicAdic("Añadir programa vinculado");
                programa = null;
                break;
            case ("commandButtonAddLinea"):
                this.setPantallaEdicionAdic("/pages/opciones/carta/editarLineaInvest.xhtml");
                this.setTituloEdicAdic("Añadir línea de inestigación");
                inveLineaInvestProyec = new InveLineaInvestProyec();
                break;
        }
//        RequestContext.getCurrentInstance().update("dlgEditAdic");

    }

    public void processDelButton(ActionEvent ae) {
        switch (ae.getComponent().getId()) {
            case ("commandButtonDelProg"):
                if (programaProyectoSelected != null) {
                    listaProgVincul.remove(programaProyectoSelected);
                }
                break;
        }
        RequestContext.getCurrentInstance().execute("PF('widgetVardataTableProgVincu').unselectAllRows();");
        RequestContext.getCurrentInstance().update("formEdit:dataTableProgVincu");
    }

    @Override
    public void updateAdicButton_processAction(ActionEvent ae) {
        switch (botonAdd) {
            case ("commandButtonProg"):
                for (InveProgramaProyecto ipp : listaProgVincul) {
                    if (ipp.getPrograma().equals(programa)) {
                        this.setMensaje("Error. Programa ya seleccionado !");
                        programa = null;
                        this.setIconMensaje("error.png");
                        RequestContext.getCurrentInstance().update("dialogMensaje");
                        RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");
                        return;
                    }
                }
                InveProgramaProyecto ipp = new InveProgramaProyecto(new InveProgramaProyectoPK(cartaEdit.getPryCodigo(), programa.getPrgCodigo()));
                ipp.setPrograma(programa);
                listaProgVincul.add(ipp);
                RequestContext.getCurrentInstance().execute("PF('widgetVardataTableProgVincu').unselectAllRows();");
                RequestContext.getCurrentInstance().update("formEdit:dataTableProgVincu");
                break;
            case ("commandButtonAddLinea"):
                inveLineaInvestProyec.setLipAreaAcad(areaCodigo);
                inveLineaInvestProyec.setLipNombre(inveLineaInvestProyec.getLipNombre().toUpperCase());
                inveLineaInvestProyecFacade.create(inveLineaInvestProyec);
                listaLineaInvestProyec = inveLineaInvestProyecFacade.findBy(areaCodigo);
                lipCodigo = inveLineaInvestProyec.getLipCodigo();
                RequestContext.getCurrentInstance().update("formEdit");
                break;
        }

        super.updateAdicButton_processAction(ae);
    }
//</editor-fold>

    @Override
    public void onFilter() {
        cartaSelected = null;
        super.onFilter();
    }

    @Override
    public void onRowSelect(SelectEvent event) {
        switch (event.getComponent().getId()) {
            case ("dataTableProgVincu"):
                break;
            case ("dataTableCarta"):
                try {
                    cartaEdit = cartaSelected.clone();
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(CartaJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
        super.onRowSelect(event);
    }

    @Override
    public void onRowUnselect(UnselectEvent event) {
        switch (event.getComponent().getId()) {
            case ("dataTableProgVincu"):
                programaProyectoSelected = null;
                break;
            case ("dataTableCarta"):
                cartaSelected = null;
                cartaEdit = null;
                break;
        }
        super.onRowUnselect(event);
    }

    @Override
    public String opcionItem(String op) {
        super.opcionItem(op);
        if (super.getOpcion().equals("actualizar")) {
            if (cartaEdit.getInveProyecto().getPrinCategoriaInvest().getCaiCodigo().equals(new Short("5"))) {
                // en el caso de doctorandos, se necesita una constancia
                super.setPantallaEdicion("/pages/opciones/carta/editarConstancia.xhtml");
                super.setTituloModalEdicion("Constancia");
            } else {
                super.setPantallaEdicion("/pages/opciones/carta/editarCarta.xhtml");
                super.setTituloModalEdicion("Carta de pertinencia");
                List<PrinDocumento> temp = prinDocumentoFacade.getDocumentos(modulo, "INVE_PROYECTO", cartaEdit.getPryCodigo());
                for (PrinDocumento doc : temp) {
                    if (doc.getPrinClaseDocumento().getClsDocumento().equals("Proyecto")) {
                        docPry = doc;
                        break;
                    }
                }
                listaProgVincul = inveProgramaProyectoFacade.findBy(cartaEdit);
                lipCodigo = (cartaEdit.getInveLineaInvestProyec() == null ? null : cartaEdit.getInveLineaInvestProyec().getLipCodigo());
            }
            this.setVisibleBotonGrabar(cartaEdit.getCarFechaPertinencia() == null);
        }
        return null;
    }

    @Override
    public void preUpdate() {
        if (cartaEdit.getCarRazonPertinencia() != null && cartaEdit.getCarRazonPertinencia().trim().isEmpty()) {
            cartaEdit.setCarRazonPertinencia(null);
        }
        if (!cartaEdit.getInveProyecto().getPrinCategoriaInvest().getCaiCodigo().equals(new Short("5"))) {
            // cuando la categoría del investigador NO es doctorando
            cartaEdit.setInveLineaInvestProyec(new InveLineaInvestProyec(lipCodigo));
            cartaEdit.setInveProgramaProyectoCollection(listaProgVincul);
        } else {
            cartaEdit.setCarEsPertinente("S");
        }
        cartaEdit.setCarFechaRecep(new Date());
        cartaEdit.setCarUsuarioRecep(usr.getUsuCedPass());
    }

    @Override
    public void update() {
        inveCartaFacade.edit(cartaEdit);
    }

    @Override
    public void postUpdate() {
        if (cartaEdit.getInveProyecto().getPrinCategoriaInvest().getCaiCodigo().equals(new Short("5"))) {
            this.setMensaje("Constancia registrada !");
        } else {
            this.setMensaje("Carta de pertinencia registrada y notificada !");
        }
        this.setIconMensaje("visto.jpg");
        RequestContext.getCurrentInstance().update("dialogMensaje");
        RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");
        super.postUpdate();
        RequestContext.getCurrentInstance().update("formCarta");
    }

}
