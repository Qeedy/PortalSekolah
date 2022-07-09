package com.portalSekolah.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portalSekolah.model.ModelKelas;
import com.portalSekolah.model.ModelUserKelas;
import com.portalSekolah.service.KelasService;

@RestController
public class KelasController {

	@Autowired
	private KelasService kelasService;
	
	@GetMapping("/getAllkelas")
	public List<ModelKelas> getAllKelas(){
		return new ArrayList<>();
	}; 
	
	@GetMapping("/test")
	public List<ModelKelas> getTest(){
		return new ArrayList<>();
	};
	
	@PostMapping(value = "/create-kelas", produces = { "application/json" }, consumes = { "application/json" })
	@RolesAllowed("GURU")
	public String createKelas(@RequestBody ModelKelas kelas) {
		return kelasService.createKelas(kelas); 
	}
	
	@GetMapping("/kelas-detail/{uuid}")
	private ModelKelas getKelasDetail(@PathVariable String uuid) {
		return kelasService.getKelasDetail(uuid);
	}
	
	@PostMapping("/add-user-kelas/{uuid}")
	public String addUserKelas(@PathVariable String uuid, 
			@RequestBody ModelUserKelas modelUserKelas) {
		kelasService.addUserKelas(uuid, modelUserKelas);
		return uuid;
	}
	
	@DeleteMapping("/delete-kelas/{uuid}")
	public void deleteKelas(@PathVariable String uuid) {
		kelasService.deleteKelas(uuid);
	}
}
