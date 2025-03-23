package com.github.maikoncarlos.pix.mapper;

import com.github.maikoncarlos.pix.controller.dto.PixRequestDTO;
import com.github.maikoncarlos.pix.controller.dto.PixResponseDTO;
import com.github.maikoncarlos.pix.repository.entity.Pix;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPixMapper {
    Pix requestToEntity(PixRequestDTO request);

    PixResponseDTO toResponseDTO(Pix request);
}
