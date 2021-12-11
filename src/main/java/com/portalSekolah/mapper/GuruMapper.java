package com.portalSekolah.mapper;

import org.springframework.stereotype.Component;

import com.portalSekolah.entity.Guru;
import com.portalSekolah.entity.User;
import com.portalSekolah.model.ModelGuru;

@Component
public class GuruMapper {

	public static ModelGuru mapperEntityToModel(Guru guru) {
		return ModelGuru.builder()
				.emailAddress(guru.getEmailAddress())
				.namaLengkap(guru.getNamaLengkap())
				.alamat(guru.getAlamat())
				.gender(guru.getGender())
				.noTelp(guru.getNoTelp())
				.build();
	}
	
	public static Guru mapperModelToEntity(ModelGuru modelGuru) {
		Guru guru = new Guru();
		guru.setEmailAddress(modelGuru.emailAddress());
		guru.setGender(modelGuru.gender());
		guru.setNamaLengkap(modelGuru.namaLengkap());
		guru.setAlamat(modelGuru.alamat());
		guru.setNoTelp(modelGuru.noTelp());
		return guru;
	}
	
	public static Guru mapperUserToEntity(User user) {
		Guru guru = new Guru();
		guru.setNamaLengkap(user.getNamaDepan()+" "+user.getNamaBelakang());
		guru.setEmailAddress(user.getEmailAddress());
		guru.setUuid(user.getUuid());
		guru.setAlamat(user.getAlamat());
		guru.setGender(user.getGender());
		guru.setNoTelp(user.getNoTelp());
		return guru;
	}
}
