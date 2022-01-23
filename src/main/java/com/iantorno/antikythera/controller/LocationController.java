package com.iantorno.antikythera.controller;

import com.iantorno.antikythera.model.LocationNode;
import com.iantorno.antikythera.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    LocationRepository locationDataRepository;

    @GetMapping("")
    public ResponseEntity<List<LocationNode>> getAllLocations(@RequestParam(required = false) String title) {
        try {
            List<LocationNode> locationNodes = new ArrayList<>();

            if (title == null) {
                locationNodes.addAll(locationDataRepository.findAll());
            } else {
                locationNodes.addAll(locationDataRepository.findByTitleContaining(title));
            }

            if (locationNodes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(locationNodes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationNode> getLocationById(@PathVariable("id") String id) {
        Optional<LocationNode> locationData = locationDataRepository.findById(id);

        if (locationData.isPresent()) {
            return new ResponseEntity<>(locationData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<LocationNode>> findByActive() {
        try {
            List<LocationNode> locationData = locationDataRepository.findByActive(true);

            if (locationData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(locationData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity createLocationData(@RequestBody LocationNode locationNode) {
        try {
            LocationNode _locationNode = locationDataRepository.save(locationNode);
            return new ResponseEntity<>(_locationNode, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationNode> updateLocationData(@PathVariable("id") String id, @RequestBody LocationNode locationNode) {
        Optional<LocationNode> storedLocationData = locationDataRepository.findById(id);

        if (storedLocationData.isPresent()) {
            LocationNode _locationNode = storedLocationData.get();
            _locationNode.setTitle(locationNode.getTitle());
            _locationNode.setDescription(locationNode.getDescription());
            _locationNode.setActive(locationNode.isActive());
            return new ResponseEntity<>(locationDataRepository.save(_locationNode), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteLocationData(@PathVariable("id") String id) {
        try {
            locationDataRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllLocationData() {
        try {
            locationDataRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
