package com.gdgu.mvc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.NoArgsConstructor;

@Entity
@lombok.Data
@NoArgsConstructor
public class Data {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int sNo;
    private String data;

    public Data(String data) {
        this.data = data;
    }
}