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
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String description;

    private String states = "000000000000000000000";

    public Task(String description) {
        this.description = description;
    }

}