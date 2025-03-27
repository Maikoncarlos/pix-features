package com.github.maikoncarlos.pix.repository;

import com.github.maikoncarlos.pix.repository.entity.Pix;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPixRepository extends MongoRepository<Pix, UUID> {

    Optional<Pix> findByIdAndActive(UUID id, boolean active);

    List<Pix> findByAgencyNumberAndAccountNumberAndActive(int agencyNumber, int accountNumber, boolean active);

    boolean existsByIdAndActive(UUID id, boolean active);

    boolean existsByKeyValue(String keyValue);

    List<Pix> findByKeyType(String keyType);

    List<Pix> findByKeyTypeAndClientName(String keyType, String clientName);
}