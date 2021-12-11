package com.portalSekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portalSekolah.entity.Siswa;
import com.portalSekolah.entity.User;
import com.portalSekolah.repository.SiswaRepository;

@Service
public class SiswaService {
	
	@Autowired
	private SiswaRepository siswaRepository;

	public void createSiswa(User user) {
		Siswa siswa = mappingSiswa(user);
		siswaRepository.save(siswa);
	}
	
	public Siswa mappingSiswa(User user) {
		Siswa siswa = new Siswa();
		siswa.setEmailAddress(user.getEmailAddress());
		siswa.setGender(user.getGender());
		siswa.setNamaLengkap(user.getNamaDepan() + " " + user.getNamaBelakang());
		siswa.setNoTelp(user.getNoTelp());
		siswa.setUuid(user.getUuid());
		return siswa;
	}
	
	
}
