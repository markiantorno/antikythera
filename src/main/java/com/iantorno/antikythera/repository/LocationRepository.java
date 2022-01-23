package com.iantorno.antikythera.repository;

import com.iantorno.antikythera.model.LocationNode;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LocationRepository extends MongoRepository<LocationNode, String> {
    List<LocationNode> findByTitleContaining(String title);
    List<LocationNode> findByActive(boolean active);
}
