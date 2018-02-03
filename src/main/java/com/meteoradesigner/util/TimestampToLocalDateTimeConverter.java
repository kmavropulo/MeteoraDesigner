package com.meteoradesigner.util;

import org.springframework.util.Assert;

//Dependency hibernate.core added to add the implementation's interface.
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

//For all entities in auto mode, spring-context's Asserts.
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
