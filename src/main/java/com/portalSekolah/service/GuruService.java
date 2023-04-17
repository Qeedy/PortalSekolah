package com.portalSekolah.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.portalSekolah.entity.Guru;
import com.portalSekolah.entity.User;
import com.portalSekolah.mapper.GuruMapper;
import com.portalSekolah.model.ModelGuru;
import com.portalSekolah.repository.GuruRepository;

@Service
public class GuruService {

    private static final Logger log = LoggerFactory
            .getLogger(GuruService.class);

    @Autowired
    private GuruRepository guruRepository;

    public List<ModelGuru> findGuru(String keyword, Pageable pageable) {
        if (StringUtils.isNotBlank(keyword)) {
            return guruRepository.findByNamaLengkapContains(keyword, pageable)
                    .stream().map(GuruMapper::mapperEntityToModel)
                    .collect(Collectors.toList());
        } else {
            return guruRepository.findAllGuru(pageable)
                    .stream().map(GuruMapper::mapperEntityToModel)
                    .collect(Collectors.toList());
        }
    }

    public ModelGuru getGuruDetail(String idGuru) {
        Guru guru = guruRepository.findById(idGuru)
                .orElseThrow(NullPointerException::new);
        return GuruMapper.mapperEntityToModel(guru);
    }

    public void createGuru(User user) {
        Guru guru = GuruMapper.mapperUserToEntity(user);
        guruRepository.save(guru);
        log.info("Guru Created {}", guru.getNamaLengkap());
    }

    public String updateGuru(String idGuru, ModelGuru updateGuru) {
        Guru guru = GuruMapper.mapperModelToEntity(updateGuru);
        guru.setUuid(idGuru);
        guruRepository.save(guru);
        return idGuru;
    }

    public void deleteGuru(String idGuru) {
        guruRepository.deleteById(idGuru);
    }

}
