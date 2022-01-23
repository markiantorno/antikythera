package com.iantorno.antikythera.repository;

import com.iantorno.antikythera.model.LocationNode;
import com.iantorno.antikythera.model.TimelineNode;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TimelineRepository extends MongoRepository<TimelineNode, String> {
    List<TimelineNode> findByTitleContaining(String title);
    List<TimelineNode> findByActive(boolean active);
}
