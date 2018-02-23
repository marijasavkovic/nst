package com.master.nst.model.thematicunit;

import java.io.Serializable;

public class ThematicUnit implements Serializable{

    private Long id;
    private String name;
    private String serialNumber;
    private String description;
    private ThematicUnit parentThematicUnit;

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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(final String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public ThematicUnit getParentThematicUnit() {
        return parentThematicUnit;
    }

    public void setParentThematicUnit(final ThematicUnit parentThematicUnit) {
        this.parentThematicUnit = parentThematicUnit;
    }

    @Override
    public String toString() {
        return "ThematicUnit{" + "id=" + id + ", name='" + name + '\'' + ", serialNumber='" + serialNumber + '\''
            + ", description='" + description + '\'' + ", parentThematicUnit=" + parentThematicUnit + '}';
    }
}
