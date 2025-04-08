package com.sirius1b.product.models;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "categories")
public class Category {

    @Id
    private UUID id;
    private String name;
    private String description;
}