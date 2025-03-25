package com.github.maikoncarlos.pix.controller;

import com.github.maikoncarlos.pix.controller.dto.PixCreatResponseDTO;
import com.github.maikoncarlos.pix.controller.dto.PixRequestDTO;
import com.github.maikoncarlos.pix.controller.dto.PixResponseDTO;
import com.github.maikoncarlos.pix.controller.dto.PixUpdateRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public sealed interface IPixController permits PixController{

    ResponseEntity<PixCreatResponseDTO> created(PixRequestDTO requestDTO);
    ResponseEntity<PixResponseDTO> findById(String id);
    ResponseEntity<List<PixResponseDTO>> findListByAgencyAndAccount(int agency, int account);
    ResponseEntity<PixResponseDTO> update(PixUpdateRequestDTO requestDTO);
    ResponseEntity<PixResponseDTO> disableById(String id);
}
