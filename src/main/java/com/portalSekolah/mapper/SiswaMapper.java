package com.portalSekolah.mapper;

import com.portalSekolah.entity.Siswa;
import com.portalSekolah.entity.User;
import com.portalSekolah.model.ModelSiswa;

public class SiswaMapper {
	
	public static ModelSiswa mapperEntityToModel(Siswa siswa) {
		return ModelSiswa.builder()
				.emailAddress(siswa.getEmailAddress())
				.namaLengkap(siswa.getNamaLengkap())
				.alamat(siswa.getAlamat())
				.gender(siswa.getGender())
				.noTelp(siswa.getNoTelp())
				.build();
	}
	
	public static Siswa mapperModelToEntity(ModelSiswa modelSiswa) {
		Siswa siswa = new Siswa();
		siswa.setEmailAddress(modelSiswa.emailAddress());
		siswa.setGender(modelSiswa.gender());
		siswa.setNamaLengkap(modelSiswa.namaLengkap());
		siswa.setNoTelp(modelSiswa.noTelp());
		return siswa;
	}
	
	public static Siswa mapperUserToEntity(User user) {
		Siswa siswa = new Siswa();
		siswa.setNamaLengkap(user.getNamaDepan()+" "+user.getNamaBelakang());
		siswa.setEmailAddress(user.getEmailAddress());
		siswa.setUuid(user.getUuid());
		siswa.setGender(user.getGender());
		siswa.setNoTelp(user.getNoTelp());
		return siswa;
	}

}
