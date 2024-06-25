package com.localidad.restclient;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.localidad.restclient.model.Canton;
import com.localidad.restclient.model.Parroquia;
import com.localidad.restclient.model.Provincia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

@Service
public class ApiClient {

	private final RestTemplate restTemplate;
	public ApiClient() {
		this.restTemplate = new RestTemplate();
		
	}
	
	private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public List<Provincia>  getListProvincias(String url) throws IOException, InterruptedException {
        
    	List<Provincia> lstProvincias = new ArrayList<Provincia>();
    	
    	HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String strProvincias = response.body();
        JSONObject jsonProvincias = new JSONObject(strProvincias);
        
        
        //List<Provincia> lstProvincia = new ArrayList<Provincia>(); 
        
        
        try
        {
        for (String keyCodProv : jsonProvincias.keySet()) {
        	Provincia provincia = new Provincia();
        	String nombreProvincia = jsonProvincias.getJSONObject(keyCodProv).getString("provincia");
        	provincia.setIdProvincia(Long.parseLong(keyCodProv));
        	provincia.setNombreProvincia(nombreProvincia);
        	
        	System.out.println("Provincias codigo: " + keyCodProv + " - Nombre prov:  " + nombreProvincia );
        	
            Object cantones = jsonProvincias.getJSONObject(keyCodProv).getJSONObject("cantones");
            if (cantones instanceof JSONObject) {

                
                JSONObject lstCantones = (JSONObject)cantones;
                
                List<Canton> lstTempCantones = new ArrayList<Canton>();
                
                for(String llave : lstCantones.keySet()) {
                	String nombreCanton = lstCantones.getJSONObject(llave).getString("canton");
                	Canton canton = new Canton(); 
                	canton.setIdCanton(Long.parseLong(llave));
                	canton.setNombreCanton(nombreCanton);
                	
                	Object parroquias = lstCantones.getJSONObject(llave).getJSONObject("parroquias");
                	JSONObject lstParroquias = (JSONObject)parroquias;
                	System.out.println("Cantones - codigo: " + llave + " - nombreCanton: " + nombreCanton);
                	List<Parroquia> lstTempParroquias = new ArrayList<Parroquia>();
                	for(String idParroquia : lstParroquias.keySet()) {
                		String nombreParroquia = lstParroquias.get(idParroquia).toString();
                		Parroquia parroquia = new Parroquia();
                		parroquia.setIdParroquia(Long.parseLong(idParroquia));
                		parroquia.setNombreParroquia(nombreParroquia);
                		System.out.println(" Parroquias idParroquia: " + idParroquia + ": nombreParroquia: " + nombreParroquia );
                		
                		lstTempParroquias.add(parroquia);
                	
	                	}
	                	canton.setLstParroquias(lstTempParroquias);
	                	lstTempCantones.add(canton);
                	}
                provincia.setLstCantones(lstTempCantones);
                //provincia.getLstCantones().add(canton);
                }
            lstProvincias.add(provincia);
            }  
        }catch(Exception ex)
        {
        	
        	System.out.println(ex.getMessage());
        	System.out.println(ex.getStackTrace());
        }

        return lstProvincias;
    }
	/*
	public Object[] getListProvincias(String url) {
		Object[] lstProvincias= null;
		try
		{
			lstProvincias = restTemplate.getForObject(url, Object[].class);
		}
		catch(Exception ex)
		{
			
			
		}
		return lstProvincias;
        
    }

    public List<Canton> getListCantones(String url) {
        Canton[] lstCantones = restTemplate.getForObject(url, Canton[].class);
        return Arrays.asList(lstCantones);
    }

    public List<Parroquia> getListParroquias(String url) {
        Parroquia[] lstParroquias = restTemplate.getForObject(url, Parroquia[].class);
        return Arrays.asList(lstParroquias);
    }
    */
}
