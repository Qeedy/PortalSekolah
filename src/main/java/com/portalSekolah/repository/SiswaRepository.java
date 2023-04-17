package com.portalSekolah.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.portalSekolah.entity.Siswa;

public interface SiswaRepository extends CrudRepository<Siswa, String> {

    Siswa findByNamaLengkap(String namaLengkap);

    @Query("SELECT s FROM Siswa s WHERE (:keywords IS NULL or s.namaLengkap LIKE CONCAT('%',UPPER(:keywords),'%'))")
    List<Siswa> findAllSiswa(@Param("keywords") String keywords);

}
