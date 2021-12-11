package com.portalSekolah.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.portalSekolah.entity.Siswa;
import com.portalSekolah.entity.UserKelas;
import com.portalSekolah.model.ModelUserKelas;
import com.portalSekolah.repository.SiswaRepository;

@Component
public class UserKelasMapper {
	
	private static Logger logger = LoggerFactory.getLogger(UserKelasMapper.class);
	
	@Autowired
	private SiswaRepository siswaRepository;
	
	public static ModelUserKelas mapperEntityToModal(UserKelas userKelas) {
		return ModelUserKelas.builder()
				.namaLengkap(userKelas.getNamaLengkap())
				.noTelp(userKelas.getNoTelp())
				.statusUser(userKelas.getStatusUser())
				.idKelas(userKelas.getKelas().getUuid())
				.build();
	}
	
	public UserKelas mapperModalToEntity(ModelUserKelas modelUserKelas) {
		UserKelas userKelas = new UserKelas();
		try {
			Siswa siswa = siswaRepository.findById(modelUserKelas.idSiswa()).orElseThrow(Exception::new);
			userKelas.setSiswa(siswa);
		}catch (Exception e) {
			logger.debug("can't load siswa");
		}
		userKelas.setNamaLengkap(modelUserKelas.namaLengkap());
		userKelas.setNoTelp(modelUserKelas.noTelp());
		userKelas.setStatusUser(modelUserKelas.statusUser());
		return userKelas;
	}

}
