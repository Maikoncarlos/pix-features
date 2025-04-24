package com.github.maikoncarlos.pix.service;

import com.github.maikoncarlos.pix.controller.dto.PixRequestDTO;
import com.github.maikoncarlos.pix.controller.dto.PixUpdateRequestDTO;
import com.github.maikoncarlos.pix.enumType.KeyType;
import com.github.maikoncarlos.pix.exception.*;
import com.github.maikoncarlos.pix.mapper.IPixMapper;
import com.github.maikoncarlos.pix.repository.IPixRepository;
import com.github.maikoncarlos.pix.repository.entity.Pix;
import com.github.maikoncarlos.pix.service.validation.ValidKeyValueStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static ch.qos.logback.core.util.StringUtil.isNullOrEmpty;
import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class PixService {

    private final IPixRepository pixRepository;
    private final IPixMapper pixMapper;

    static final String DEFAULT_LOG = "[LOG] PixService";

    @Transactional
    public Pix save(final PixRequestDTO pixRequestDTO) throws PixKeyValueAlreadyRegisteredException {
        log.info (DEFAULT_LOG + " ValidaKeyValueStrategy ");
        final var validKeyValueStrategy = new ValidKeyValueStrategy (KeyType.getKeyType (pixRequestDTO.keyType ()));
        validKeyValueStrategy.execute (pixRequestDTO.keyValue ());

        if (keyValueAlreadyRegistered (pixRequestDTO.keyValue ())) {
            log.error (DEFAULT_LOG + " keyValueAlreadyRegistered ");
            throw new PixKeyValueAlreadyRegisteredException (pixRequestDTO.keyValue ());
        }

        return pixRepository.save (pixMapper.requestToEntity (pixRequestDTO));
    }

    public Pix findById(final String id) {
        return pixRepository.findByIdAndActive (UUID.fromString (id), true).orElseThrow (() -> {
            log.error (DEFAULT_LOG + " findByIdAndActive ");
            return new PixByIdNotFoundException (id);
        });
    }

    public List<Pix> findListByAgencyAndAccount(final int agency, final int account) {
        final var responseFindAgencyAndAccount = pixRepository.findByAgencyNumberAndAccountNumberAndActive (agency, account, true);

        if (responseFindAgencyAndAccount.isEmpty ()){
            log.error (DEFAULT_LOG + " responseFindAgencyAndAccount ");
            throw new PixByAgencyAndAccountNotFoundException (agency, account);
        }

        return responseFindAgencyAndAccount;
    }

    @Transactional
    public Pix updatePix(final PixUpdateRequestDTO pixUpdateRequestDTO) {
        var responseFindById = findById (pixUpdateRequestDTO.id ());

        responseFindById.setAccountType (pixUpdateRequestDTO.accountType ());
        responseFindById.setAgencyNumber (pixUpdateRequestDTO.agencyNumber ());
        responseFindById.setAccountNumber (pixUpdateRequestDTO.accountNumber ());
        responseFindById.setClientName (pixUpdateRequestDTO.clientName ());
        responseFindById.setClientLastName (pixUpdateRequestDTO.clientLastName ());

        return pixRepository.save (responseFindById);
    }

    @Transactional
    public Pix disableById(String id) {
        if (pixAlreadyDisable (id)){
            log.error (DEFAULT_LOG + " pixAlreadyDisable ");
            throw new PixExistsByIdException (id);
        }

        Pix pix = findById (id);
        pix.setActive (false);
        pix.setDateOfInactivation (LocalDateTime.now ());

        return pixRepository.save (pix);
    }

    private boolean pixAlreadyDisable(String id) {
        return pixRepository.existsByIdAndActive (UUID.fromString (id), false);
    }

    private boolean keyValueAlreadyRegistered(final String keyValue) {
        return pixRepository.existsByKeyValue (keyValue);
    }

    public List<Pix> findByKeyTypeAndOrClientName(final String keyType, final String clientName) throws PixKeyTypeNotFoundException {
        KeyType.getKeyType (keyType);

        if (isNullOrEmpty (clientName)) {
            log.warn (DEFAULT_LOG + " isNullOrEmpty ");
            final var pixListKeyType = getByKeyType (keyType);

            return validPixIsEmpty (keyType, pixListKeyType);
        }

        final var pixListKeyTypeAndClientName = pixRepository.findByKeyTypeAndClientName (keyType, clientName);
        return validPixIsEmpty (keyType, clientName, pixListKeyTypeAndClientName);
    }

    private static List<Pix> validPixIsEmpty(String keyType, List<Pix> pixList) throws PixKeyTypeNotFoundException {
        if (pixList.isEmpty ()) {
            log.error (DEFAULT_LOG + " pixList ");
            throw new PixKeyTypeNotFoundException (keyType);
        }

        return pixList;
    }

    private static List<Pix> validPixIsEmpty(String keyType, String clientName, List<Pix> pixList) throws PixKeyTypeNotFoundException {
        if (pixList.isEmpty ()) {
            log.error (DEFAULT_LOG + " pixList ");
            throw new PixKeyTypeNotFoundException (format (" %s , %s ", keyType, clientName));
        }

        return pixList;
    }

    private List<Pix> getByKeyType(String keyType) {
        return pixRepository.findByKeyType (keyType);
    }
}