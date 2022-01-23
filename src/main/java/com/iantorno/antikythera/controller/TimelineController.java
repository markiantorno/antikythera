package com.iantorno.antikythera.controller;

import com.iantorno.antikythera.model.TimelineNode;
import com.iantorno.antikythera.repository.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/timelines")
public class TimelineController {

    @Autowired
    TimelineRepository timelineRepository;

    @GetMapping("")
    public ResponseEntity<List<TimelineNode>> getAllTimelines(@RequestParam(required = false) String title) {
        try {
            List<TimelineNode> timelineNodes = new ArrayList<>();

            if (title == null) {
                timelineNodes.addAll(timelineRepository.findAll());
            } else {
                timelineNodes.addAll(timelineRepository.findByTitleContaining(title));
            }

            if (timelineNodes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(timelineNodes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimelineNode> getTimelineById(@PathVariable("id") String id) {
        Optional<TimelineNode> timelineNode = timelineRepository.findById(id);

        if (timelineNode.isPresent()) {
            return new ResponseEntity<>(timelineNode.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<TimelineNode>> findByActive() {
        try {
            List<TimelineNode> timeline = timelineRepository.findByActive(true);

            if (timeline.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(timeline, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity createTimeline(@RequestBody TimelineNode timelineNode) {
        try {
            TimelineNode _timelineNode = timelineRepository.save(timelineNode);
            return new ResponseEntity<>(_timelineNode, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimelineNode> updateTimelineData(@PathVariable("id") String id, @RequestBody TimelineNode timelineNode) {
        Optional<TimelineNode> storedTimeline = timelineRepository.findById(id);

        if (storedTimeline.isPresent()) {
            TimelineNode _timelineNode = storedTimeline.get();
            _timelineNode.setTitle(timelineNode.getTitle());
            _timelineNode.setActive(timelineNode.isActive());
            return new ResponseEntity<>(timelineRepository.save(_timelineNode), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTimeline(@PathVariable("id") String id) {
        try {
            timelineRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllTimelines() {
        try {
            timelineRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
