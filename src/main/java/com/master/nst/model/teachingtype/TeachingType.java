package com.master.nst.model.teachingtype;

import java.io.Serializable;

public class TeachingType implements Serializable {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.getName())
            .append("id")
            .append(id)
            .append("name")
            .append(name).toString();
    }
}
