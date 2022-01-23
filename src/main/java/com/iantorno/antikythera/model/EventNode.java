package com.iantorno.antikythera.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TypeAlias("event")
public class EventNode extends BaseNode {
    public String description;
    public String startDate;
    public String endDate;

    @Builder
    public EventNode(String title, String description, boolean active) {
        this.title = title;
        this.description = description;
        this.active = active;
    }
}
