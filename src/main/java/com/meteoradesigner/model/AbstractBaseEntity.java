package com.meteoradesigner.model;

import com.meteoradesigner.HasId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class - abstract base entity, part of implementation of Enhanced Entity Relationships (EER)
 * model.
 *
 * @see <a href=http://cs-exhibitions.uni-klu.ac.at/index.php?id=431>EER</a>
 */
//Overriding id methods.
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class AbstractBaseEntity implements HasId {
    private Integer id = null;

    @Override
    public String toString() {
        return String.format("Entity{%s" +
                "(id=%s)}", getClass().getName(), getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractBaseEntity that = (AbstractBaseEntity) o;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
