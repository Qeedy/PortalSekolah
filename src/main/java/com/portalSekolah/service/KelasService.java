package com.portalSekolah.service;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.portalSekolah.entity.Kelas;
import com.portalSekolah.mapper.KelasMapper;
import com.portalSekolah.mapper.UserKelasMapper;
import com.portalSekolah.model.ModelKelas;
import com.portalSekolah.model.ModelUserKelas;
import com.portalSekolah.repository.KelasRepository;

@Service
public class KelasService {

	@Autowired
	private KelasRepository kelasRepository;
	@Autowired
	private KelasMapper kelasMapper;
	@Autowired
	private UserKelasMapper userKelasMapper;
	
	public Page<Kelas> getKelas(String keyword, Pageable pageable) {
		if (StringUtils.isNotBlank(keyword)) {
			return kelasRepository.findAllKelasByKeyword(keyword, pageable);
		} else {
			return kelasRepository.findAllPageAble(pageable);
		}
	}

	public ModelKelas getKelasDetail(String uuidKelas) {
		Kelas kelas = kelasRepository.findById(uuidKelas).orElseThrow(NullPointerException::new);
		return KelasMapper.mapperEntityToModel(kelas);
	}

	public String createKelas(ModelKelas modelKelas) {
		Kelas kelas = kelasMapper.mapperModelToEntity(modelKelas);
		String uuid = UUID.randomUUID().toString();
		kelas.setUuid(uuid);
		kelasRepository.save(kelas);
		return uuid;
	}

	public String updateKelas(String idKelas, ModelKelas updateKelas) {
		Kelas kelas = kelasMapper.mapperModelToEntity(updateKelas);
		kelas.setUuid(idKelas);
		kelasRepository.save(kelas);
		return idKelas;
	}

	public void deleteKelas(String idKelas) {
		kelasRepository.deleteById(idKelas);
	}
	
	public void addUserKelas(String uuid, ModelUserKelas ukModel) {
		Kelas kelas = kelasRepository.findById(uuid).orElseThrow(NullPointerException::new);
		kelas.addUserKelas(userKelasMapper.mapperModalToEntity(ukModel));
		kelasRepository.save(kelas);
	}

}
