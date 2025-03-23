package com.github.maikoncarlos.pix.service;

import com.github.maikoncarlos.pix.controller.dto.PixRequestDTO;
import com.github.maikoncarlos.pix.exception.PixNotFoundException;
import com.github.maikoncarlos.pix.mapper.IPixMapper;
import com.github.maikoncarlos.pix.repository.IPixRepository;
import com.github.maikoncarlos.pix.repository.entity.Pix;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PixService {

    private final IPixRepository pixRepository;
    private final IPixMapper pixMapper;

    public PixService(IPixRepository pixRepository, IPixMapper pixMapper) {
        this.pixRepository = pixRepository;
        this.pixMapper = pixMapper;
    }

    public Pix save(final PixRequestDTO pixRequestDTO) {
        return pixRepository.save (pixMapper.requestToEntity (pixRequestDTO));
    }

    public Pix findById(String id) {
        return pixRepository.findByUuidAndActive (UUID.fromString (id), true).
                orElseThrow (() -> new PixNotFoundException (id));
    }
}
