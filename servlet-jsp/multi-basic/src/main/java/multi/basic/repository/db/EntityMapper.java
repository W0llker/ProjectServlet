package multi.basic.repository.db;

import java.lang.reflect.*;
import java.util.*;

public class EntityMapper<T> {
    private Class<T> tClass;

    public EntityMapper(Class<T> tClass) {
        this.tClass = tClass;
    }

    public T mapperInEntity(Map<String, String> s) {
        try {
            T entity = tClass.newInstance();
            for (Map.Entry<String, String> entry : s.entrySet()) {
                String field = entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
                Method m = tClass.getDeclaredMethod("set" + field, String.class);
                m.setAccessible(true);
                m.invoke(entity, new Object[]{entry.getValue()});
                m.setAccessible(false);
            }
            return entity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
