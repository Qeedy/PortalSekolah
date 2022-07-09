package com.portalSekolah.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.portalSekolah.entity.Guru;
import com.portalSekolah.entity.Kelas;
import com.portalSekolah.entity.UserKelas;
import com.portalSekolah.model.ModelKelas;
import com.portalSekolah.repository.GuruRepository;

@Component
public class KelasMapper {

	@Autowired
	private UserKelasMapper userKelasMapper;
	@Autowired
	private GuruRepository guruRepository;

	public static ModelKelas mapperEntityToModel(Kelas kelas) {
		return ModelKelas.builder().namaKelas(kelas.getNamaKelas()).namaWaliKelas(kelas.getNamaWaliKelas())
				.idGuru(kelas.getGuru().getUuid())
				.users(kelas.getUsers().stream().map(UserKelasMapper::mapperEntityToModal).collect(Collectors.toList()))
				.build();
	}

	public Kelas mapperModelToEntity(ModelKelas modelKelas) {
		Kelas kelas = new Kelas();
		if (modelKelas.users() != null) {
			List<UserKelas> users = modelKelas.users().stream().map(userKelasMapper::mapperModalToEntity)
					.collect(Collectors.toList());
			kelas.setUsers(users);
		}
		Guru guru = guruRepository.findById(modelKelas.idGuru()).orElseThrow(NullPointerException::new);
		kelas.setNamaKelas(modelKelas.namaKelas());
		kelas.setGuru(guru);
		kelas.setNamaWaliKelas(modelKelas.namaWaliKelas());
		return kelas;
	}

}
