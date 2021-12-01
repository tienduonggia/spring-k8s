package com.giatien.springk8s.model;


import com.giatien.springk8s.common.audit.FruitAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Builder
public class Fruit extends FruitAudit {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String season;

    public Fruit(){
    }

    public Fruit(int id, String name, String season) {
        this.id = id;
        this.name = name;
        this.season = season;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
