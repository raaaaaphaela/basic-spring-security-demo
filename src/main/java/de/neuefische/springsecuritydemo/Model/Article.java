package de.neuefische.springsecuritydemo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private String id;
    private String name;
    private String createdBy;

}
