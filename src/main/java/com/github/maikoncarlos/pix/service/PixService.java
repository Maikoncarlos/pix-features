package com.github.maikoncarlos.pix.service;

import com.github.maikoncarlos.pix.controller.dto.PixRequestDTO;
import com.github.maikoncarlos.pix.controller.dto.PixUpdateRequestDTO;
import com.github.maikoncarlos.pix.enumType.KeyType;
import com.github.maikoncarlos.pix.exception.PixByAgencyAndAccountNotFoundException;
import com.github.maikoncarlos.pix.exception.PixByIdNotFoundException;
import com.github.maikoncarlos.pix.exception.PixExistsByIdException;
import com.github.maikoncarlos.pix.mapper.IPixMapper;
import com.github.maikoncarlos.pix.repository.IPixRepository;
import com.github.maikoncarlos.pix.repository.entity.Pix;
import com.github.maikoncarlos.pix.service.validation.ValidKeyValueStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PixService {

    private final IPixRepository pixRepository;
    private final IPixMapper pixMapper;

    @Transactional
    public Pix save(final PixRequestDTO pixRequestDTO) {
        final var validKeyValueStrategy = new ValidKeyValueStrategy (KeyType.getKeyType (pixRequestDTO.keyType ()));
        validKeyValueStrategy.execute (pixRequestDTO.keyValue ());

        return pixRepository.save (pixMapper.requestToEntity (pixRequestDTO));
    }

    public Pix findById(final String id) {
        return pixRepository.findByIdAndActive (UUID.fromString (id), true).
                orElseThrow (() -> new PixByIdNotFoundException (id));
    }

    public List<Pix> findListByAgencyAndAccount(final int agency, final int account) {
        final var responseFindAgencyAndAccount = pixRepository.findByAgencyNumberAndAccountNumberAndActive (agency, account, true);

        if (responseFindAgencyAndAccount.isEmpty ())
            throw new PixByAgencyAndAccountNotFoundException (agency, account);

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
        if (pixRepository.existsByIdAndActive (UUID.fromString (id), false))
            throw new PixExistsByIdException (id);

        Pix pix = findById (id);
        pix.setActive (false);
        pix.setDateOfInactivation (LocalDateTime.now ());

        return pixRepository.save (pix);
    }
}