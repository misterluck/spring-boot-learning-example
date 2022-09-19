package org.example.proguard.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class City implements Serializable {
    private Long id;

    private String code;

    private String name;

    private String description;

    private Long provinceId;
}
