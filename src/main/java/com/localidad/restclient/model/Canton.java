//package com.poblacion.webclientpractica.model;
package com.localidad.restclient.model;

import java.util.ArrayList;
import java.util.List;

public class Canton {

	private Long idCanton;
	private String nombreCanton;
	//private Long idProvincia;
	private List<Parroquia> lstParroquias = new ArrayList<Parroquia>();
	
	public Long getIdCanton() {
		return idCanton;
	}
	public void setIdCanton(Long idCanton) {
		this.idCanton = idCanton;
	}
	public String getNombreCanton() {
		return nombreCanton;
	}
	public void setNombreCanton(String nombreCanton) {
		this.nombreCanton = nombreCanton;
	}
	public List<Parroquia> getLstParroquias() {
		return lstParroquias;
	}
	public void setLstParroquias(List<Parroquia> lstParroquias) {
		this.lstParroquias = lstParroquias;
	}

	
	
	

}
