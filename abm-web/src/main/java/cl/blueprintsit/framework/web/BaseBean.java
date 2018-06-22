package cl.blueprintsit.framework.web;

import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by Blueprints on 4/7/2015.
 */
public class BaseBean {

    private static Logger LOGGER = Logger.getLogger(BaseBean.class);

    public static <T> T findBean(String beanName, Class<T> beanClass) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", beanClass);
    }

    protected void showNotImplemented(String targetId) {
        showWarn(targetId, "No implementado", "Esta funcionalidad no ha sido implementada aún.");
    }

    protected void showMessage(String targetId, String title, String message) {
        showInfo(targetId, title, message);
    }

    protected void showInfo(String targetId, String title, String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, title, message);
        FacesContext.getCurrentInstance().addMessage(targetId, facesMessage);
    }

    protected void showError(String targetId, String title, String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message);
        FacesContext.getCurrentInstance().addMessage(targetId, facesMessage);
    }

    protected void showWarn(String targetId, String title, String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, title, message);
        FacesContext.getCurrentInstance().addMessage(targetId, facesMessage);
    }

    protected void showFatal(String targetId, String title, String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, title, message);
        FacesContext.getCurrentInstance().addMessage(targetId, facesMessage);
    }

    protected void showInfo(String title, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, message));
    }

    protected void showError(String title, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message));
    }

    protected void showWarn(String title, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, title, message));
    }

    protected void showFatal(String title, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, title, message));
    }

    protected void showException(String targetId, Exception e) {
        if ( e == null ) {
            showFatal(targetId, "Error", "Ocurrió una excepción inesperada.");
        } else {
            String message = e.getMessage();
            Throwable throwable = e.getCause();
            if (message == null)
                showFatal(targetId, e.getClass().getSimpleName(), throwable != null ? throwable.getMessage() : "Excepción inesperada: " + e);
            else
                showFatal(targetId, e.getClass().getSimpleName(), message);
        }
    }

    public void showExceptionRecursive(String targetId, Throwable e) {
        if ( e == null ) {
            showFatal(targetId, "Error", "Ocurrió una excepción inesperada.");
        } else {
            Throwable cause = e;
            String message = cause.getMessage();
            while (cause.getCause() != null) {
                cause = cause.getCause();
                message = cause.getMessage() != null ? cause.getMessage() : message;
            }
            showFatal(targetId, cause.getClass().getSimpleName(), message);
        }
    }

    protected void showOperationError(String targetId, Exception e, String message) {
        showError(targetId, "Error", message);
        if (e instanceof javax.ejb.EJBException)
            showExceptionRecursive(targetId, e);
        else
            showException(targetId, e);
    }
}
