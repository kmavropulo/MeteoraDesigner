package com.meteoradesigner.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Class - abstract named entity, part of implementation of Enhanced Entity Relationships (EER)
 * model.
 *
 * @see <a href=http://cs-exhibitions.uni-klu.ac.at/index.php?id=431>EER</a>
 * Named entity core is using to extend the semantic of the AbstractBaseEntity.
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    @NotBlank
    @Size(min = 1, max = 333)
    private String displayName;

    /**
     * The all-args constructor.
     */
    AbstractNamedEntity(Integer idToSet, String nameToSet) {
        super(idToSet);
        this.displayName = nameToSet;
    }

    @Override
    public String toString() {
        return String.format("NamedEntity{%s" +
                "(displayName=%s)}" + getClass().getName(), displayName);
    }
}
