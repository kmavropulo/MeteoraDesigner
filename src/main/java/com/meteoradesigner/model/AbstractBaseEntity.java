package com.meteoradesigner.model;

import com.meteoradesigner.HasId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class - abstract base entity, part of Entity Relationships (ER) model.
 *
 * @see <a href=http://cs-exhibitions.uni-klu.ac.at/index.php?id=431>ER</a>
 * Core semantic is extending the ability of keeping of the ID.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

//Overriding here.
@Getter
@Setter
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
