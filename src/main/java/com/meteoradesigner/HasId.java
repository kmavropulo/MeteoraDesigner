package com.meteoradesigner;

public interface HasId {
    Integer getId();
    void setId(Integer idToSet);
    default boolean isNew(){
        return getId()==null;
    }
}
