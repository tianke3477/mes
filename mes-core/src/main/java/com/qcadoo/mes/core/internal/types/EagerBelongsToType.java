package com.qcadoo.mes.core.internal.types;

import java.util.LinkedHashMap;
import java.util.Map;

import com.qcadoo.mes.core.api.DataDefinitionService;
import com.qcadoo.mes.core.api.Entity;
import com.qcadoo.mes.core.model.DataDefinition;
import com.qcadoo.mes.core.model.FieldDefinition;
import com.qcadoo.mes.core.search.Order;
import com.qcadoo.mes.core.search.SearchResult;
import com.qcadoo.mes.core.types.BelongsToType;

public final class EagerBelongsToType implements BelongsToType {

    private final DataDefinitionService dataDefinitionService;

    private final String lookupFieldName;

    private final String pluginIdentifier;

    private final String entityName;

    public EagerBelongsToType(final String pluginIdentifier, final String entityName, final String lookupFieldName,
            final DataDefinitionService dataDefinitionService) {
        this.pluginIdentifier = pluginIdentifier;
        this.entityName = entityName;
        this.lookupFieldName = lookupFieldName;
        this.dataDefinitionService = dataDefinitionService;
    }

    @Override
    public boolean isSearchable() {
        return false;
    }

    @Override
    public boolean isOrderable() {
        return false;
    }

    @Override
    public boolean isAggregable() {
        return false;
    }

    @Override
    public Class<?> getType() {
        return Object.class;
    }

    @Override
    public Map<Long, String> lookup(final String prefix) {
        SearchResult resultSet = getDataDefinition().find().orderBy(Order.asc(lookupFieldName)).list();
        Map<Long, String> possibleValues = new LinkedHashMap<Long, String>();

        for (Entity entity : resultSet.getEntities()) {
            possibleValues.put(entity.getId(), (String) entity.getField(lookupFieldName));
        }

        return possibleValues;
    }

    @Override
    public Object toObject(final FieldDefinition fieldDefinition, final Object value, final Entity validatedEntity) {
        return value;
    }

    @Override
    public String toString(final Object value) {
        return String.valueOf(((Entity) value).getId());
    }

    @Override
    public DataDefinition getDataDefinition() {
        return dataDefinitionService.get(pluginIdentifier, entityName);
    }

    @Override
    public String getLookupFieldName() {
        return lookupFieldName;
    }

}
