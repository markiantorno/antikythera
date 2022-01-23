package com.iantorno.antikythera.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "nodes")
public class BaseNode {

    @Id
    public String id;
    @Indexed(unique = true)
    public String title;
    protected Date createdAt;
    protected Date updatedAt;
    public boolean active = true;
    public List<BaseNode> nodes = new ArrayList<>();

}
