package com.portalSekolah.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portalSekolah.entity.Siswa;
import com.portalSekolah.entity.User;
import com.portalSekolah.model.ModelSiswa;
import com.portalSekolah.repository.SiswaRepository;

@Service
public class SiswaService {

    private static final Logger log = LoggerFactory
            .getLogger(SiswaService.class);

    @Autowired
    private SiswaRepository siswaRepository;

    public void createSiswa(User user) {
        Siswa siswa = mappingSiswa(user);
        siswaRepository.save(siswa);
        log.info("Siswa Created {}", siswa.getNamaLengkap());
    }

    public List<Siswa> getAllSiswa(String keywords) {
        return siswaRepository.findAllSiswa(keywords);
    }

    public Siswa mappingSiswa(User user) {
        Siswa siswa = new Siswa();
        siswa.setEmailAddress(user.getEmailAddress());
        siswa.setGender(user.getGender());
        siswa.setNamaLengkap(
                user.getNamaDepan().toUpperCase() + " "
                        + user.getNamaBelakang().toUpperCase());
        siswa.setNoTelp(user.getNoTelp());
        siswa.setUuid(user.getUuid());
        siswa.setAlamat(user.getAlamat());
        return siswa;
    }

}
