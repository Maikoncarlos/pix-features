package com.github.maikoncarlos.pix.controller;

import com.github.maikoncarlos.pix.controller.dto.PixCreatResponseDTO;
import com.github.maikoncarlos.pix.controller.dto.PixRequestDTO;
import com.github.maikoncarlos.pix.controller.dto.PixResponseDTO;
import com.github.maikoncarlos.pix.mapper.IPixMapper;
import com.github.maikoncarlos.pix.service.PixService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/pix")
public final class PixController implements IPixController {

    private final PixService pixService;
    private final IPixMapper pixMapper;

    public PixController(PixService pixService, IPixMapper pixMapper) {
        this.pixService = pixService;
        this.pixMapper = pixMapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<PixCreatResponseDTO> created(@RequestBody @Valid PixRequestDTO requestDTO) {
        final var savePix = pixService.save (requestDTO);

        return ResponseEntity.ok (new PixCreatResponseDTO (savePix.getUuid ().toString ()));
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<PixResponseDTO> findById(@PathVariable String id) {
        final var response = pixMapper.toResponseDTO (pixService.findById (id));

        return ResponseEntity.ok ().body (response);

    }

    @Override
    @GetMapping(value = "/{agency}/{account}")
    public ResponseEntity<List<PixResponseDTO>> findListByAgencyAndAccount(@PathVariable int agency, @PathVariable int account) {
        final var listResponse = pixMapper.toListResponse (pixService.findListByAgencyAndAccount (agency, account));

        return ResponseEntity.ok ().body (listResponse);
    }


}