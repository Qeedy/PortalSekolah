package com.portalSekolah.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portalSekolah.mapper.SiswaMapper;
import com.portalSekolah.model.ModelSiswa;
import com.portalSekolah.service.SiswaService;

@RestController
@RequestMapping("siswa")
public class SiswaController {

    @Autowired
    private SiswaService siswaService;

    @GetMapping(value = "/get-all-siswa", produces = "application/json")
    @PreAuthorize("hasUserRole('GURU')")
    public List<ModelSiswa> getAllSiswa(String keywords) {
        return siswaService.getAllSiswa(keywords).stream()
                .map(s -> SiswaMapper.mapperEntityToModel(s))
                .collect(Collectors.toList());
    };
    
//    @PostMapping(value = "/create-siswa", produces = "application/json")
//    @PreAuthorize("hasUserRole('ADMIN')")
//    public 

}
