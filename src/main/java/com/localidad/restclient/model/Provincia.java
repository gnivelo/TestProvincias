//package com.poblacion.webclientpractica.model;
package com.localidad.restclient.model;
import java.util.ArrayList;
import java.util.List;
public class Provincia {
	
	private Long idProvincia;
	private String nombreProvincia;
	private List<Canton> lstCantones = new ArrayList<Canton>();
	//private List<MaestroCanton> lstCantones;
	public Long getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String getNombreProvincia() {
		return nombreProvincia;
	}
	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}
	public List<Canton> getLstCantones() {
		return lstCantones;
	}
	public void setLstCantones(List<Canton> lstCantones) {
		this.lstCantones = lstCantones;
	}
	
	
	


}
