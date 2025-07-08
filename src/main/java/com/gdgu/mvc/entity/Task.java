package com.gdgu.mvc.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.gdgu.mvc.util.State;

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

    @Enumerated(EnumType.STRING)
    private State state;

    public Task(String description, State state) {
        this.description = description;
        this.state = state;
    }
}