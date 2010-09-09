package com.qcadoo.mes.core.data.internal.types;

import java.util.List;

import com.qcadoo.mes.core.data.api.DictionaryService;
import com.qcadoo.mes.core.data.definition.DataFieldDefinition;
import com.qcadoo.mes.core.data.types.EnumeratedFieldType;
import com.qcadoo.mes.core.data.validation.ValidationResults;

public final class DictionaryType implements EnumeratedFieldType {

    private final String dictionaryName;

    private final DictionaryService dictionaryService;

    public DictionaryType(final String dictionaryName, final DictionaryService dictionaryService) {
        this.dictionaryName = dictionaryName;
        this.dictionaryService = dictionaryService;
    }

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
    public List<String> values() {
        return dictionaryService.values(dictionaryName);
    }

    @Override
    public Class<?> getType() {
        return String.class;
    }

    @Override
    public Object toObject(final DataFieldDefinition fieldDefinition, final Object value,
            final ValidationResults validationResults) {
        String stringValue = String.valueOf(value);
        if (!values().contains(stringValue)) {
            validationResults.addError(fieldDefinition, "commons.validate.field.error.invalidDictionaryItem",
                    String.valueOf(values()));
            return null;
        }
        return stringValue;
    }

    @Override
    public String toString(final Object value) {
        return String.valueOf(value);
    }

}
