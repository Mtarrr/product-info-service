package com.github.mtarrr.pis.service;

import com.github.mtarrr.pis.model.HasNullFields;
import com.github.mtarrr.pis.model.entity.ProductOfferingEntity;

import java.lang.reflect.Field;

public class PatchUtils {
    public static <E extends HasNullFields> E patchEntity(E storedEntity, E patchedEntity) {
        Class classObject = patchedEntity.getClass();
        Field[] fields = classObject.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!(field.get(patchedEntity) == null) || (patchedEntity.getNullFields().contains(field.getName()))) {
                    field.set(storedEntity, field.get(patchedEntity));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return storedEntity;
    }

    public static ProductOfferingEntity patchProductOfferingEntity(ProductOfferingEntity storedEntity, ProductOfferingEntity patchedEntity) {
        patchEntity(storedEntity.getBody(), patchedEntity.getBody());

        return storedEntity;
    }
}
