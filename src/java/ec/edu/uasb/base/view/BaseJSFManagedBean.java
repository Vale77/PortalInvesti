/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.base.view;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import javax.faces.context.FacesContext;
import org.primefaces.event.CloseEvent;

/**
 *
 * @author victor.barba
 */
public class BaseJSFManagedBean implements Serializable {

    private Object selectedRow;
    private String opcion;
    private List<String> listaWidgetPantallas = new ArrayList<>();
    private String embedded = "&embedded=true";

    //<editor-fold defaultstate="collapsed" desc="propiedades">
    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public Object getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(Object selectedRow) {
        this.selectedRow = selectedRow;
    }

    public List<String> getListaWidgetPantallas() {
        return listaWidgetPantallas;
    }

    public void setListaWidgetPantallas(List<String> listaWidgetPantallas) {
        this.listaWidgetPantallas = listaWidgetPantallas;
    }

    public String getEmbedded() {
        return embedded;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Menu - Botones">
    private boolean tieneMenuBar = false;
    private boolean disabledNew = false;
    private boolean disabledDelete = true;
    private boolean disabledEdit = true;
    private boolean disabledSave = true;
    private boolean disabledSearch = true;

    private boolean visibleSearch = false;
    private boolean visiblePrint = false;
    private boolean visibleSave = false;
    private boolean visibleNew = false;
    private boolean visibleDelete = false;
    private boolean visibleEdit = false;

    public boolean isTieneMenuBar() {
        return tieneMenuBar;
    }

    public void setTieneMenuBar(boolean tieneMenuBar) {
        this.tieneMenuBar = tieneMenuBar;
    }

    public boolean isDisabledNew() {
        return disabledNew;
    }

    public void setDisabledNew(boolean disabledNew) {
        this.disabledNew = disabledNew;
    }

    public boolean isDisabledDelete() {
        return disabledDelete;
    }

    public void setDisabledDelete(boolean disabledDelete) {
        this.disabledDelete = disabledDelete;
    }

    public boolean isDisabledEdit() {
        return disabledEdit;
    }

    public void setDisabledEdit(boolean disabledEdit) {
        this.disabledEdit = disabledEdit;
    }

    public boolean isDisabledSearch() {
        return disabledSearch;
    }

    public void setDisabledSearch(boolean disabledSearch) {
        this.disabledSearch = disabledSearch;
    }

    public boolean isVisibleSearch() {
        return visibleSearch;
    }

    public void setVisibleSearch(boolean visibleSearch) {
        this.visibleSearch = visibleSearch;
    }

    public boolean isDisabledSave() {
        return disabledSave;
    }

    public void setDisabledSave(boolean disabledSave) {
        this.disabledSave = disabledSave;
    }

    public boolean isVisiblePrint() {
        return visiblePrint;
    }

    public void setVisiblePrint(boolean visiblePrint) {
        this.visiblePrint = visiblePrint;
    }

    public boolean isVisibleSave() {
        return visibleSave;
    }

    public void setVisibleSave(boolean visibleSave) {
        this.visibleSave = visibleSave;
    }

    public boolean isVisibleNew() {
        return visibleNew;
    }

    public void setVisibleNew(boolean visibleNew) {
        this.visibleNew = visibleNew;
    }

    public boolean isVisibleDelete() {
        return visibleDelete;
    }

    public void setVisibleDelete(boolean visibleDelete) {
        this.visibleDelete = visibleDelete;
    }

    public boolean isVisibleEdit() {
        return visibleEdit;
    }

    public void setVisibleEdit(boolean visibleEdit) {
        this.visibleEdit = visibleEdit;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="pantalla edicion principal">
    private String pantallaEdicion;
    private String posicionEdicion = "center";
    private String tituloModalEdicion;
    private boolean visibleBotonGrabar = true;
    private boolean visibleBotonCancelar = true;
    private String widthEditDisplay = "auto";
    private String heightEditDisplay = "auto";

    public String getWidthEditDisplay() {
        return widthEditDisplay;
    }

    public void setWidthEditDisplay(String widthEditDisplay) {
        this.widthEditDisplay = widthEditDisplay;
    }

    public String getHeightEditDisplay() {
        return heightEditDisplay;
    }

    public void setHeightEditDisplay(String heightEditDisplay) {
        this.heightEditDisplay = heightEditDisplay;
    }

    public String getPosicionEdicion() {
        return posicionEdicion;
    }

    public void setPosicionEdicion(String posicionEdicion) {
        this.posicionEdicion = posicionEdicion;
    }

    public String getPantallaEdicion() {
        return pantallaEdicion;
    }

    public void setPantallaEdicion(String pantallaEdicion) {
        this.pantallaEdicion = pantallaEdicion;
    }

    public String getTituloModalEdicion() {
        return tituloModalEdicion;
    }

    public void setTituloModalEdicion(String tituloModalEdicion) {
        this.tituloModalEdicion = tituloModalEdicion;
    }

    public boolean isVisibleBotonGrabar() {
        return visibleBotonGrabar;
    }

    public void setVisibleBotonGrabar(boolean visibleBotonGrabar) {
        this.visibleBotonGrabar = visibleBotonGrabar;
    }

    public boolean isVisibleBotonCancelar() {
        return visibleBotonCancelar;
    }

    public void setVisibleBotonCancelar(boolean visibleBotonCancelar) {
        this.visibleBotonCancelar = visibleBotonCancelar;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="pantalla edicion item adicional">    
    private String pantallaEditAdicItem;
    private boolean renderEditAdicItem = false;
    private String tituloEditAdicItem;
    private boolean disabledConfirmEditAdicItem = true;
    private String botonEditAdicItemGrabar = "Grabar";
    private String botonEditAdicItemCancelar = "Cancelar";
    private String posicionEditAdicItem = "center";

    public String getPantallaEditAdicItem() {
        return pantallaEditAdicItem;
    }

    public void setPantallaEditAdicItem(String pantallaEditAdicItem) {
        this.pantallaEditAdicItem = pantallaEditAdicItem;
    }

    public boolean isRenderEditAdicItem() {
        return renderEditAdicItem;
    }

    public void setRenderEditAdicItem(boolean renderEditAdicItem) {
        this.renderEditAdicItem = renderEditAdicItem;
    }

    public String getTituloEditAdicItem() {
        return tituloEditAdicItem;
    }

    public void setTituloEditAdicItem(String tituloEditAdicItem) {
        this.tituloEditAdicItem = tituloEditAdicItem;
    }

    public boolean isDisabledConfirmEditAdicItem() {
        return disabledConfirmEditAdicItem;
    }

    public void setDisabledConfirmEditAdicItem(boolean disabledConfirmEditAdicItem) {
        this.disabledConfirmEditAdicItem = disabledConfirmEditAdicItem;
    }

    public String getBotonEditAdicItemGrabar() {
        return botonEditAdicItemGrabar;
    }

    public void setBotonEditAdicItemGrabar(String botonEditAdicItemGrabar) {
        this.botonEditAdicItemGrabar = botonEditAdicItemGrabar;
    }

    public String getBotonEditAdicItemCancelar() {
        return botonEditAdicItemCancelar;
    }

    public void setBotonEditAdicItemCancelar(String botonEditAdicItemCancelar) {
        this.botonEditAdicItemCancelar = botonEditAdicItemCancelar;
    }

    public String getPosicionEditAdicItem() {
        return posicionEditAdicItem;
    }

    public void setPosicionEditAdicItem(String posicionEditAdicItem) {
        this.posicionEditAdicItem = posicionEditAdicItem;
    }

    public void resetEditAdicItem() {
    }

    public void updateEditAdicItemButton_processAction(ActionEvent ae) {
        RequestContext.getCurrentInstance().execute("PF('widgetVarEditAdicItem').hide();");
    }

    public void addNuevoItemButton_processAction(ActionEvent ae) {
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="pantalla edicion adicional">
    private String pantallaEdicionAdic;
    private boolean renderEdicAdic = false;
    private String tituloEdicAdic;
    private boolean disabledConfirmEdicAdic = true;
    private String botonAdicGrabar = "Grabar";
    private boolean visibleBotonAdicGrabar = true;
    private String botonAdicCancelar = "Cancelar";
    private boolean visibleBotonAdicCancelar = true;
    private String posicionEditAdic = "center";
    private boolean visiblePantallaEdicAdic = false;

    public boolean isVisiblePantallaEdicAdic() {
        return visiblePantallaEdicAdic;
    }

    public void setVisiblePantallaEdicAdic(boolean visiblePantallaEdicAdic) {
        this.visiblePantallaEdicAdic = visiblePantallaEdicAdic;
    }

    public String getPantallaEdicionAdic() {
        return pantallaEdicionAdic;
    }

    public void setPantallaEdicionAdic(String pantallaEdicionAdic) {
        this.pantallaEdicionAdic = pantallaEdicionAdic;
    }

    public boolean isRenderEdicAdic() {
        return renderEdicAdic;
    }

    public void setRenderEdicAdic(boolean renderEdicAdic) {
        this.renderEdicAdic = renderEdicAdic;
    }

    public String getTituloEdicAdic() {
        return tituloEdicAdic;
    }

    public void setTituloEdicAdic(String tituloEdicAdic) {
        this.tituloEdicAdic = tituloEdicAdic;
    }

    public boolean isDisabledConfirmEdicAdic() {
        return disabledConfirmEdicAdic;
    }

    public void setDisabledConfirmEdicAdic(boolean disabledConfirmEdicAdic) {
        this.disabledConfirmEdicAdic = disabledConfirmEdicAdic;
    }

    public String getBotonAdicGrabar() {
        return botonAdicGrabar;
    }

    public void setBotonAdicGrabar(String botonAdicGrabar) {
        this.botonAdicGrabar = botonAdicGrabar;
    }

    public boolean isVisibleBotonAdicGrabar() {
        return visibleBotonAdicGrabar;
    }

    public void setVisibleBotonAdicGrabar(boolean visibleBotonAdicGrabar) {
        this.visibleBotonAdicGrabar = visibleBotonAdicGrabar;
    }

    public boolean isVisibleBotonAdicCancelar() {
        return visibleBotonAdicCancelar;
    }

    public void setVisibleBotonAdicCancelar(boolean visibleBotonAdicCancelar) {
        this.visibleBotonAdicCancelar = visibleBotonAdicCancelar;
    }

    public String getBotonAdicCancelar() {
        return botonAdicCancelar;
    }

    public void setBotonAdicCancelar(String botonAdicCancelar) {
        this.botonAdicCancelar = botonAdicCancelar;
    }

    public String getPosicionEditAdic() {
        return posicionEditAdic;
    }

    public void setPosicionEditAdic(String posicionEditAdic) {
        this.posicionEditAdic = posicionEditAdic;
    }

    public void resetAdic() {
    }

    public void updateAdicButton_processAction(ActionEvent ae) {
        RequestContext.getCurrentInstance().execute("PF('dlgEditAdic').hide();");
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="PDF">
    private String tituloPDF;
    private boolean renderPDF = false;
    private boolean repEmbebVisible;

    public void setTituloPDF(String tituloPDF) {
        this.tituloPDF = tituloPDF;
    }

    public String getTituloPDF() {
        return tituloPDF;
    }

    public boolean isRenderPDF() {
        return renderPDF;
    }

    public void setRenderPDF(boolean renderPDF) {
        this.renderPDF = renderPDF;
    }

    public boolean isRepEmbebVisible() {
        return repEmbebVisible;
    }

    public void setRepEmbebVisible(boolean repEmbebVisible) {
        this.repEmbebVisible = repEmbebVisible;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Mensaje final">
    private String msgDlg;
    private String iconMensaje = "visto.jpg";
    private String mensaje = null;

    public String getIconMensaje() {
        return iconMensaje;
    }

    public void setIconMensaje(String iconMensaje) {
        this.iconMensaje = iconMensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getMsgDlg() {
        return msgDlg;
    }

    public void setMsgDlg(String msgDlg) {
        this.msgDlg = msgDlg;
    }

//</editor-fold>
    public BaseJSFManagedBean() {
    }

    @PostConstruct
    public void postConstruct() {
        init();
    }

    public void reset() {
        this.setRepEmbebVisible(false);
        selectedRow = null;
    }

    public void init() {
        menuDefault();
        deSeleccionarListas();
        reset();
    }

    public void refreshMenuBar(boolean valor) {
        if (tieneMenuBar) {
            this.setDisabledEdit(valor);
            this.setDisabledDelete(valor);
            RequestContext.getCurrentInstance().update("formMenuBar");
        }
    }

    public void menuDefault() {
        if (tieneMenuBar) {
            this.visibleNew = true;
            this.visibleEdit = true;
            this.visibleDelete = true;
            refreshMenuBar(true);
        }
    }

    public void deSeleccionarListas() {
        for (String widget : listaWidgetPantallas) {
            RequestContext.getCurrentInstance().execute("PF('" + widget + "').unselectAllRows();");
        }
    }

    public void onFilter() {
        refreshMenuBar(true);
    }

    public void onRowSelect(SelectEvent event) {
        refreshMenuBar(false);
    }

    public void onRowUnselect(UnselectEvent event) {
        refreshMenuBar(true);
    }

    public static String formatDate(Date currentDate) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        return dateFormat.format(currentDate);
    }

    public void cancelarButton() {
        menuDefault();
        deSeleccionarListas();
        reset();
    }

    public String print() {
        return null;
    }

    public String search() {
        return null;
    }

    public String opcionItem(String op) {
        msgDlg = null;
        opcion = op;
        switch (opcion) {
            case "insertar":
                msgDlg = "Registro nuevo.<br/><br/>Esta usted seguro de guardarlo ?";
                break;
            case "actualizar":
                msgDlg = "Datos modificados.<br/><br/>Esta usted seguro de salvarlos ?";
                break;
            default:
        }
        return null;
    }

    public boolean preDelete() {
        return true;
    }

    public void deleteButton_processAction(ActionEvent ae) {
        if (this.preDelete() == false) {
            return;
        }
        try {
            this.delete();
            postDelete();
        } catch (Exception e) {
        }

    }

    public void delete() {

    }

    public void postDelete() {
        init();
        RequestContext.getCurrentInstance().update("form");
    }

    public void insert() {

    }

    public boolean checkRules() {
        return true;
    }

    public void preUpdate() {

    }

    public void updateButton_processAction(ActionEvent ae) {
        try {
            if (this.checkRules() == false) {
                this.setIconMensaje("error.png");
                RequestContext.getCurrentInstance().update("dialogMensaje");
                RequestContext.getCurrentInstance().execute("PF('mensajeWidget').show();");
                return;
            }
            this.preUpdate();
            if (opcion.equals("actualizar")) {
                this.update();
            } else {
                this.insert();
            }
            postUpdate();
        } catch (Exception e) {
        }

    }

    public void postUpdate() {
        init();
        RequestContext.getCurrentInstance().execute("PF('dlgEdit').hide();");
        RequestContext.getCurrentInstance().update("form");
    }

    public void update() {

    }

    public void handleClose(CloseEvent event) {
        this.setTituloPDF(null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("urlRep");
    }

    public void handleCloseEdit(CloseEvent event) {
        this.cancelarButton();

    }
}
