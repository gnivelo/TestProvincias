package com.localidad.restclient.controller;

import com.localidad.restclient.ApiClient;
import com.localidad.restclient.model.Provincia;
import com.localidad.restclient.model.Canton;
import com.localidad.restclient.model.Parroquia;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MainController {
	@Autowired
	private ApiClient apiClient;
	
	/*
	@GetMapping("/test")
	public String getAll()
	{
		
		return "provincias";
		
	}
	*/
	
	@GetMapping("/test")
	public String index(Model model) throws IOException, InterruptedException {
		String ProvinciasUrl = "https://gist.githubusercontent.com/emamut/6626d3dff58598b624a1/raw/166183f4520c4603987c55498df8d2983703c316/provincias.json";
		List<Provincia> lstProvincias = apiClient.getListProvincias(ProvinciasUrl);
		
		
		model.addAllAttributes(lstProvincias);
		model.addAttribute("lstProvincias", lstProvincias);
		return "index";
	}

}
