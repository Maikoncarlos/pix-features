package com.github.maikoncarlos.pix.controller;

import com.github.maikoncarlos.pix.controller.dto.PixCreatResponseDTO;
import com.github.maikoncarlos.pix.controller.dto.PixRequestDTO;
import com.github.maikoncarlos.pix.service.PixService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/pix")
public final class PixController implements IPixController {

    private final PixService pixService;

    public PixController(PixService pixService) {
        this.pixService = pixService;
    }

    @Override
    @PostMapping
    public ResponseEntity<PixCreatResponseDTO> created(@RequestBody @Valid PixRequestDTO requestDTO) {
        final var savePix = pixService.save (requestDTO);

        return ResponseEntity.ok (new PixCreatResponseDTO (savePix.getUuid ().toString ()));
    }
}