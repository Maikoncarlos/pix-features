package com.github.maikoncarlos.pix.controller;

import com.github.maikoncarlos.pix.controller.dto.PixCreatResponseDTO;
import com.github.maikoncarlos.pix.controller.dto.PixRequestDTO;
import com.github.maikoncarlos.pix.controller.dto.PixResponseDTO;
import com.github.maikoncarlos.pix.controller.dto.PixUpdateRequestDTO;
import com.github.maikoncarlos.pix.mapper.IPixMapper;
import com.github.maikoncarlos.pix.service.PixService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/v1/pix")
@RequiredArgsConstructor
public final class PixController implements IPixController {

    private final PixService pixService;
    private final IPixMapper pixMapper;

    final String defaultLog = "[LOG] PixController,";

    @Override
    @PostMapping
    public ResponseEntity<PixCreatResponseDTO> created(@RequestBody @Valid PixRequestDTO requestDTO) {
        log.info(defaultLog + "  created ({})", requestDTO);
        final var savePix = pixService.save (requestDTO);

        return ResponseEntity.ok (new PixCreatResponseDTO (savePix.getId ().toString ()));
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<PixResponseDTO> findById(@PathVariable String id) {
        log.info(defaultLog + "  findById ({})", id);
        final var response = pixMapper.toResponseDTO (pixService.findById (id));

        return ResponseEntity.ok ().body (response);

    }

    @Override
    @GetMapping(value = "/{agency}/{account}")
    public ResponseEntity<List<PixResponseDTO>> findListByAgencyAndAccount(@PathVariable int agency, @PathVariable int account) {
        log.info(defaultLog + "  findListByAgencyAndAccount ({}, {})", agency, account);
        final var listResponse = pixMapper.toListResponse (pixService.findListByAgencyAndAccount (agency, account));

        return ResponseEntity.ok ().body (listResponse);
    }

    @Override
    @PutMapping
    public ResponseEntity<PixResponseDTO> update(@RequestBody @Valid PixUpdateRequestDTO requestDTO) {
        log.info(defaultLog + "  update ({})", requestDTO);
        final var response = pixMapper.toResponseDTO (pixService.updatePix (requestDTO));

        return ResponseEntity.ok ().body (response);
    }

    @Override
    @PatchMapping(value = "/{id}")
    public ResponseEntity<PixResponseDTO> disableById(@PathVariable String id) {
        log.info(defaultLog + "  disableById ({})", id);
        final var response = pixMapper.toResponseDTO (pixService.disableById (id));

        return ResponseEntity.ok ().body (response);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<PixResponseDTO>> findByKeyTypeAndOrClientName(@RequestParam String keyType,
                                                                             @RequestParam(required = false) String clientName) {
        log.info(defaultLog + "  findByKeyTypeAndOrClientName ({} , {})", keyType, clientName);
        final var response = pixMapper.toListResponse (
                pixService.findByKeyTypeAndOrClientName (keyType, clientName));

        return ResponseEntity.ok ().body (response);
    }
}