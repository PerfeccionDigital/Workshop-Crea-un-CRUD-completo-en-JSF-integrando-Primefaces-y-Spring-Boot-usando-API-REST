package com.perfecciondigital.workshop.util;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

public class UtilFaces {
	
	public static void addMessage(Severity severity, String summary, String detail){
		FacesContext facesContext = FacesContext.getCurrentInstance();
    	facesContext.addMessage(null, new FacesMessage(severity, summary, detail));
    }
	
	public static void invalidateSesion() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.getExternalContext().invalidateSession();
	}
	
	public static void redirect(String ruta){
    	try {
    		FacesContext facesContext = FacesContext.getCurrentInstance();
    		facesContext.getExternalContext().redirect(ruta);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public static void executeScript(String script){
		/*
		RequestContext.getCurrentInstance().execute(script); Deprecated
		*/
		PrimeFaces.current().executeScript(script);
    }

}
