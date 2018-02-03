package com.meteoradesigner.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//TODO protected or private
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    @NotNull
    @Size(min = 1, max = 333)
    private String name;

    protected AbstractNamedEntity(Integer idToSet, String nameToSet) {
        super(idToSet);
        this.name = nameToSet;
    }

    @Override
    public String toString() {
        return String.format("NamedEntity{%s" +
                "(name=%s)}" + getClass().getName(), name);
    }
}
