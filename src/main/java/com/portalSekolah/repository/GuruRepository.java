package com.portalSekolah.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.portalSekolah.entity.Guru;

public interface GuruRepository extends CrudRepository<Guru, String>{

	Page<Guru> findByNamaLengkapContains(String namaLengkap, Pageable pageable);
	
	@Query("SELECT g FROM Guru g")
	Page<Guru> findAllGuru(Pageable pageable);
	
}
