package com.nefee.pokedaycare.view.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class FacesUtils {

	public static ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}

	public static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public static String getContextPath() {
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		return servletContext.getContextPath();
	}

//	public static String getRootURL() {
//		HttpServletRequest request = (HttpServletRequest) getExternalContext().getRequest();
//		String rootURL = request.getRequestURL().toString().replace(request.getRequestURI().substring(0), "");
//		return rootURL;
//	}

	public static void setManagedBeanInSession(String beanName, Object managedBean) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(beanName, managedBean);
	}

	public static String getRequestParameter(String name) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
	}

	public static void addInfoMessage(String msg) {
		addInfoMessage(null, msg);
	}

	public static void addInfoMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public static void addErrorMessage(String msg) {
		addErrorMessage(null, msg);
	}

	public static void addErrorMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

//	public static UserVo getCurrentUser(UserService service) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication != null) {
//			Object principal = authentication.getPrincipal();
//			if (principal != null) {
//				if (principal instanceof User) {
//					return service.getByLogin(((User) principal).getUsername());
//				}
//				if (principal instanceof UserVo) {
//					return (UserVo) principal;
//				}
//			}
//		}
//		return null;
//	}
}