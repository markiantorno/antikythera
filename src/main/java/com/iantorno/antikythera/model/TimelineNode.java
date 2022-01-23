package com.iantorno.antikythera.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TypeAlias("timeline")
public class TimelineNode extends BaseNode {

    List<EventNode> events = new ArrayList<EventNode>();

    public List<EventNode> addEvent(EventNode node) {
        events.add(node);
        return events;
    }

    public List<EventNode> removeEvent(EventNode node) {
        events.remove(node);
        return events;
    }
}
