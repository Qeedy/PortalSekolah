package com.portalSekolah.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.portalSekolah.entity.Kelas;

public interface KelasRepository extends CrudRepository<Kelas, String> {

	@Query("Select k from Kelas where (:keyword is null or k.namaWaliKelas=:keyword) or (:keyword is null or k.namaKelas=:keyword)")
	Page<Kelas> findAllKelasByKeyword(@Param("keyword") String keyword, Pageable pageable);
	
	@Query("Select k from Kelas")
	Page<Kelas> findAllPageAble(Pageable pageable);

}
