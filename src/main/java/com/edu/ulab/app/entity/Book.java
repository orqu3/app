package com.edu.ulab.app.entity;

import com.edu.ulab.app.util.IdGeneratorUtil;
import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {

    private Long id;
    private String title;
    private String author;
    private Long pageCount;

    public Book() {
        this.id = IdGeneratorUtil.generateId();
    }
}
