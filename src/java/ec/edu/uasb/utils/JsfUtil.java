package ec.edu.uasb.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

public class JsfUtil {

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static void addErrorMessage(Exception ex, String clientId, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(clientId, msg);
        } else {
            addErrorMessage(clientId, defaultMsg);
        }
    }

    public static void addErrorMessages(String clientId, List<String> messages) {
        for (String message : messages) {
            addErrorMessage(clientId, message);
        }
    }

    public static void addWarnMessage(String clientId, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
        FacesContext.getCurrentInstance().addMessage(clientId, facesMsg);
    }

    public static void addErrorMessage(String clientId, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(clientId, facesMsg);
    }

    public static void addSuccessMessage(String clientId, String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage(clientId, facesMsg);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    public static String getPerfilPermitido() throws Exception {
        String perf = null;
        try {
            /* resource-bundle de faces-config.xml*/
            ResourceBundle rb = ResourceBundle.getBundle("i18n_" + FacesContext.getCurrentInstance().getELContext().getLocale());
            /* archivo properties */
            perf = rb.getObject("app.profiles").toString();
        } catch (Exception e) {
            throw new Exception(" Revisar la función: getPerfilPermitido(). ");
        }

        String[] result = perf.split(",");
        perf = "";
        for (String r : result) {
            perf = perf + ",'" + r.trim() + "'";
        }

        return perf.substring(1);

    }

    public static StringBuilder ReadTextFile(String pathName) throws IOException {
        //leer los datos desde el archivo
        InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(pathName);
        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(inputStream));
        String line = null;
        try {
            StringBuilder appendMessage = new StringBuilder(16384);
            while ((line = in.readLine()) != null) {
                appendMessage.append(line);
                appendMessage.append('\n');
            }
            return appendMessage;
        } catch (IOException e) {
        }
        in.close();
        return null;
    }

    public static StringBuilder ReplaceKeyText(String key, StringBuilder msgSource, String str) {
        int position = msgSource.lastIndexOf(key);
        if (position == -1) {
            return msgSource;
        }
        msgSource = msgSource.replace(position, position + key.length(), str);
        return msgSource;
    }

    public static StringBuilder ReplaceKeyText(Map keys, StringBuilder msgSource) {
        for (Object key : keys.keySet()) {
            ReplaceKeyText(key.toString(), msgSource, keys.get(key).toString());
        }
        return msgSource;
    }

}
