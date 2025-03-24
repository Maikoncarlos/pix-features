package com.github.maikoncarlos.pix.repository;

import com.github.maikoncarlos.pix.repository.entity.Pix;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPixRepository extends MongoRepository<Pix, UUID> {

    Optional<Pix> findByUuidAndActive(UUID uuid, boolean active);
    List<Pix> findByAgencyNumberAndAccountNumberAndActive(int agencyNumber, int accountNumber, boolean active);

}