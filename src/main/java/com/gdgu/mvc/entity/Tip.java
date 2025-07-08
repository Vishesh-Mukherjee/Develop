package com.gdgu.mvc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Tip {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String data;

    public Tip(String description) {
        this.data = description;
    }
}
