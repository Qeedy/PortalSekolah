package com.portalSekolah.repository;

import org.springframework.data.repository.CrudRepository;

import com.portalSekolah.entity.Siswa;

public interface SiswaRepository extends CrudRepository<Siswa, String> {
	
	Siswa findByNamaLengkap(String namaLengkap);

}
