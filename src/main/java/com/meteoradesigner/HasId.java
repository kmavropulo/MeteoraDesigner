package com.meteoradesigner;

import java.io.Serializable;

public interface HasId extends Serializable {
    Integer getId();

    void setId(Integer idToSet);

    default boolean isNew() {
        return getId() == null;
    }
}
