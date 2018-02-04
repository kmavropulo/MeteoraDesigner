package com.meteoradesigner.util;

import org.springframework.util.Assert;

//Dependency hibernate.core added to add the implementation's interface.
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Class-converter from/to db data (<code>Timestamp</code>) to/from attribute
 * (<code>LocalDateTime</code>).
 * For all entities in enabled below auto mode; spring-context's asserts using for checking.
 */
@Converter(autoApply = true)
public class TimestampToLocalDateTimeConverter implements AttributeConverter<LocalDateTime
        , Timestamp> {
    //TODO test on next iteration.
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attributeLocalDateTime) {
        Assert.notNull(attributeLocalDateTime, "LocalDateTime to convert must not be null!");
        return Timestamp.valueOf(attributeLocalDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dBTimestamp) {
        Assert.notNull(dBTimestamp, "dBTimestamp to convert must not be null");
        return dBTimestamp.toLocalDateTime();
    }
}
