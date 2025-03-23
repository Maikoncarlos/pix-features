package com.github.maikoncarlos.pix.controller;

import com.github.maikoncarlos.pix.controller.dto.PixCreatResponseDTO;
import com.github.maikoncarlos.pix.controller.dto.PixRequestDTO;
import org.springframework.http.ResponseEntity;

public sealed interface IPixController permits PixController{

    ResponseEntity<PixCreatResponseDTO> created(PixRequestDTO requestDTO);
}
