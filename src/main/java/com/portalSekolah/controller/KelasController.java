package com.portalSekolah.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portalSekolah.model.ModelKelas;

@RestController
public class KelasController {

	@GetMapping("/getAllkelas")
	private List<ModelKelas> getAllKelas(){
		return new ArrayList<>();
	}; 
	@GetMapping("/test")
	private List<ModelKelas> getTest(){
		return new ArrayList<>();
	};
}
