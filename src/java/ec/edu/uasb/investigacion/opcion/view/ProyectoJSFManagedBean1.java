/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.opcion.view;

import ec.edu.uasb.base.view.BaseJSFManagedBean;
import ec.edu.uasb.external.entities.AnioAcademico;
import ec.edu.uasb.external.session.AnioAcademicoFacadeLocal;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.session.InveProyectoFacadeLocal;
import ec.edu.uasb.seg.entities.Perfil;
import ec.edu.uasb.seg.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author victor.barba
 */
@Named(value = "proyectoBean1")
@ViewScoped
public class ProyectoJSFManagedBean1 extends BaseJSFManagedBean implements Serializable {

    @EJB
    private InveProyectoFacadeLocal inveProyectoFacade;
    @EJB
    private AnioAcademicoFacadeLocal anioAcademicoFacade;

    private final Usuario usr;
    private String filtrarAreas;
    private String perfil = new String();

    private Short anio;
    private List<AnioAcademico> listaAnios = new ArrayList<>();
    private List<InveProyecto> listaProyectos = new ArrayList<>();
    private Short[] selectedGrupos;
    private InveProyecto inveProyectoEdit;
    private InveProyecto inveProyectoSelected;
    private List<SelectItem> esquema = new ArrayList<>();
    private String[] selectedItemsEsquema;
    private String archivo;

    //<editor-fold defaultstate="collapsed" desc="Propiedades y Variables">
    public List<AnioAcademico> getListaAnios() {
        return listaAnios;
    }

    public Short getAnio() {
        return anio;
    }

    public void setAnio(Short anio) {
        this.anio = anio;
    }

    public Short[] getSelectedGrupos() {
        return selectedGrupos;
    }

    public void setSelectedGrupos(Short[] selectedGrupos) {
        this.selectedGrupos = selectedGrupos;
    }

    public List<InveProyecto> getListaProyectos() {
        return listaProyectos;
    }

    public InveProyecto getInveProyectoEdit() {
        return inveProyectoEdit;
    }

    public void setInveProyectoEdit(InveProyecto inveProyectoEdit) {
        this.inveProyectoEdit = inveProyectoEdit;
    }

    public InveProyecto getInveProyectoSelected() {
        return inveProyectoSelected;
    }

    public void setInveProyectoSelected(InveProyecto inveProyectoSelected) {
        this.inveProyectoSelected = inveProyectoSelected;
    }

    public List<SelectItem> getEsquema() {
        return esquema;
    }

    public void setEsquema(List<SelectItem> esquema) {
        this.esquema = esquema;
    }

    public String[] getSelectedItemsEsquema() {
        return selectedItemsEsquema;
    }

    public void setSelectedItemsEsquema(String[] selectedItemsEsquema) {
        this.selectedItemsEsquema = selectedItemsEsquema;
    }

    public String getArchivo() {
        return archivo;
    }

    //</editor-fold>
    /**
     * Creates a new instance of GruposJSFManagedBean
     */
    public ProyectoJSFManagedBean1() {
        super.setTieneMenuBar(true);
        super.setPantallaEdicion("/pages/opciones/editarProy.xhtml");
        super.setTituloModalEdicion("Recepción de Proyectos");
        super.setPosicionEdicion("top");
        super.getListaWidgetPantallas().add("widgetProy");
        super.setWidthEditDisplay("1024");
        // seguridad en perfiles y areas
        this.usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        List<Perfil> perfiles = (List<Perfil>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfiles");
        if (perfiles != null) {
            filtrarAreas = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("filtrarInfo");// áreas inicializadas             
            for (Perfil perf : perfiles) {
                if (perf.getIdPerfil().equals("USU_SIST") || perf.getIdPerfil().equals("ADM_INVE") || perf.getIdPerfil().equals("SEC_INVE")) {
                    perfil = perf.getIdPerfil();
                    filtrarAreas = "T";
                    break;
//                } else if (perf.getIdPerfil().equals("SEC_INVE") || perf.getIdPerfil().equals("SEC_INS")) {
//                    perfil = perf.getIdPerfil();
//                    filtrarAreas = "A";
//                    break;
                }
            }
        }
        SelectItemGroup datos = new SelectItemGroup("1. DATOS DEL INVESTIGADOR");
        datos.setSelectItems(new SelectItem[]{
            new SelectItem("1", "Nombres completos"),
            new SelectItem("2", "Fecha de Nacimiento"),
            new SelectItem("3", "Cédula / Pasaporte"),
            new SelectItem("4", "Área acdémica"),
            new SelectItem("5", "Categoría del investigador"),
            new SelectItem("6", "Teléfono fijo y celular"),
            new SelectItem("7", "Dirección domiciliaria")}
        );
        SelectItemGroup elementos = new SelectItemGroup("2. ELEMENTOS DEL PROYECTO");
        elementos.setSelectItems(new SelectItem[]{
            new SelectItem("12", "Título"),
            new SelectItem("13", "Justificación"),
            new SelectItem("14", "Relevancia científica"),
            new SelectItem("15", "Objetivo general"),
            new SelectItem("16", "Objetivos específicos"),
            new SelectItem("17", "Planteamiento del problema"),
            new SelectItem("18", "Metodología"),
            new SelectItem("19", "Plan inicial de contenidos")}
        );
        SelectItemGroup recursos = new SelectItemGroup("3. RECURSOS REQUERIDOS");
        recursos.setSelectItems(new SelectItem[]{
            new SelectItem("30", "Datos personales de c/u"),
            new SelectItem("31", "Tiempo de dedicación de c/u"),
            new SelectItem("32", "Nro de meses")}
        );
        SelectItemGroup crono = new SelectItemGroup("4. CRONOGRAMA");
        crono.setSelectItems(new SelectItem[]{
            new SelectItem("41", "Mensual "),
            new SelectItem("42", "Valorado")}
        );
        SelectItemGroup presupuesto = new SelectItemGroup("5. PRESUPUESTO DEL PROYECTO");
        presupuesto.setSelectItems(new SelectItem[]{
            new SelectItem("50", "Contratación de ayudantes"),
            new SelectItem("51", "Movilización y viáticos"),
            new SelectItem("52", "Materiales y Bibliografía"),
            new SelectItem("53", "Bonificación")}
        );
        SelectItemGroup infoAdic = new SelectItemGroup("6. INFORMACION ADICIONAL");
        infoAdic.setSelectItems(new SelectItem[]{
            new SelectItem("61", "Tipo de investigación"),
            new SelectItem("62", "Código Unesco"),
            new SelectItem("63", "Disciplina Científica"),
            new SelectItem("64", "Objeto socio-ecnómico"),
            new SelectItem("65", "Ambito geográfico")}
        );
        esquema.add(datos);
        esquema.add(elementos);
        esquema.add(recursos);
        esquema.add(crono);
        esquema.add(presupuesto);
        esquema.add(infoAdic);
        archivo = "subsist=investigacion&reporte=Formulario_129472217";
    }

    @Override
    public void init() {
        listaAnios = anioAcademicoFacade.getListaAnios();
        retrieveBy();
        super.init();
    }

    @Override
    public boolean isDisabledNew() {
        this.setDisabledNew((anio == null));
        return super.isDisabledNew();
    }

    public void retrieveBy() {
        inveProyectoSelected = null;
        listaProyectos.clear();
        if (anio != null) {
            listaProyectos = inveProyectoFacade.findBy(anio,null,null);
        }
        else{
            listaProyectos = inveProyectoFacade.findConvocatoriaBy(null,null);}
    }

    //<editor-fold defaultstate="collapsed" desc="validaciones ">
    public void validateFechas(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }

        //Leave the null handling of startDate to required="true"
        Object startDateValue = component.getAttributes().get("fechaInicio");
        if (startDateValue == null) {
            return;
        }

        Date startDate = (Date) startDateValue;
        Date endDate = (Date) value;
        if (endDate.before(startDate)) {
            FacesMessage msg = new FacesMessage(" La fecha Final debe ser mayor a la fecha Inicial", " La fecha Final debe ser mayor a la fecha Inicial");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
//</editor-fold>

//    public void resetCampo(String campo) {
//        switch (campo) {
//            case ("FechaFin"):
//                convocatoriaEdit.setCvoFechaFin(null);
//                break;
//        }
//    }
    @Override
    public void onRowSelect(SelectEvent event) {
        try {
            inveProyectoEdit = inveProyectoSelected.clone();

        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ProyectoJSFManagedBean1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        super.onRowSelect(event);
    }

    @Override
    public String opcionItem(String op) {
        super.setOpcion(op);
        if (super.getOpcion().equals("actualizar")) {
            super.setRenderPDF(true);
        }
        return null;
    }

    @Override
    public void preUpdate() {
//        if (this.getOpcion().equals("actualizar")) {
//            convocatoriaEdit.setInveTipoConvocatoria(tipoConvoEdit);
//            convocatoriaEdit.setCvoFechaAct(new Date());
//            convocatoriaEdit.setCvoUsuarioAct(usr.getUsuCedPass());
//        } else {
//            convocatoriaEdit.setInveTipoConvocatoria(tipoConvoEdit);
//            convocatoriaEdit.setCvoFechaCrea(new Date());
//            convocatoriaEdit.setCvoUsuarioCrea(usr.getUsuCedPass());
//        }
//        if (!convocatoriaEdit.getCvoEstado().equals("I")) {
//            convocatoriaEdit.setCvoFechaEstado(new Date());
//        }
    }

    @Override
    public void update() {
        inveProyectoFacade.edit(inveProyectoEdit);
    }

    @Override
    public void delete() {
        inveProyectoFacade.remove(inveProyectoSelected);
    }

    @Override
    public void postUpdate() {
        this.setMensaje("Proyecto recibido !");
        this.setIconMensaje("visto.jpg");
        RequestContext.getCurrentInstance().update("dialogMensaje");
        RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");
        super.postUpdate();
        RequestContext.getCurrentInstance().update("formProy");
    }

}
