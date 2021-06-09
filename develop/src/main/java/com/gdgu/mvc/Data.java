package com.gdgu.mvc;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
class Data {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int sNo;
    private String data;
    
    public Data() {
    }

    public Data(String data) {
        this.data = data;
    }

    public int getSNo() {
        return this.sNo;
    }

    public void setSNo(int sNo) {
        this.sNo = sNo;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
            " sNo='" + getSNo() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }

}