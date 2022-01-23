package com.iantorno.antikythera.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TypeAlias("location")
public class LocationNode extends BaseNode {

    private String description;
    private String path;

    @Builder
    public LocationNode(String title, String description, String path, boolean active) {
        this.title = title;
        this.description = description;
        this.path = path;
        this.active = active;
    }
}
