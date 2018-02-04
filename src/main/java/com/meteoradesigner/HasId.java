package com.meteoradesigner;

import java.io.Serializable;

/**
 * Interface with core semantic to spread the ID attribute.
 */
public interface HasId extends Serializable {

    /**
     * Get the id.
     *
     * @return <code>Integer</code> instance id
     */
    Integer getId();

    /**
     * Set the id.
     *
     * @param idToSet the id to set
     */
    void setId(Integer idToSet);

    /**
     * Check if the subclass instance is new.
     *
     * @return true if the instance is new
     */
    default boolean isNew() {
        return getId() == null;
    }
}
