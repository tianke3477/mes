package com.qcadoo.mes.core.data.internal.types;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.qcadoo.mes.core.data.definition.DataFieldDefinition;
import com.qcadoo.mes.core.data.types.FieldType;
import com.qcadoo.mes.core.data.validation.ValidationResults;

public final class DateType implements FieldType {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public boolean isSearchable() {
        return true;
    }

    @Override
    public boolean isOrderable() {
        return true;
    }

    @Override
    public boolean isAggregable() {
        return false;
    }

    @Override
    public Class<?> getType() {
        return Date.class;
    }

    @Override
    public Object toObject(final DataFieldDefinition fieldDefinition, final Object value,
            final ValidationResults validationResults) {
        if (value instanceof Date) {
            return value;
        }
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(String.valueOf(value));
        } catch (ParseException e) {
            validationResults.addError(fieldDefinition, "commons.validate.field.error.invalidDateFormat");
        }
        return null;
    }

    @Override
    public String toString(final Object value) {
        return new SimpleDateFormat(DATE_FORMAT).format((Date) value);
    }

}
