package com.edu.ulab.app.entity;

import com.edu.ulab.app.util.IdGeneratorUtil;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Long id;
    private String fullName;
    private String title;
    private int age;

    public User() {
        this.id = IdGeneratorUtil.generateId();
    }
}
