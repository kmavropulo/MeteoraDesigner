package com.meteoradesigner.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * This @code{ContextDto} class declares data transfer object for @code{Context} class.
 */
@Setter
@Getter
@NoArgsConstructor
public class ContextDto extends AbstractNamedDto {

    @NotBlank
    private String userId;

    @Size(max = 6400)
    private String description;

    /**
     * The all-args constructor.
     */
    public ContextDto(Integer idToSet,
                      String displayNameToSet,
                      String userIdToSet,
                      String descriptionToSet
    ) {
        super(idToSet, displayNameToSet);
        this.userId = userIdToSet;
        this.description = descriptionToSet;
    }
}