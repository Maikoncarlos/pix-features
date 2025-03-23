package com.github.maikoncarlos.pix.repository;

import com.github.maikoncarlos.pix.repository.entity.Pix;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface IPixRepository extends MongoRepository<Pix, UUID> {
}
