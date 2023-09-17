package multi.basic.repository.db;


import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DbWork<T> {
    protected EntityMapper<T> entityMapper;

    public DbWork(EntityMapper<T> entityMapper) {
        this.entityMapper = entityMapper;
    }

    protected List<T> getEntity(ResultSet resultSet, Object o) throws SQLException {
            List<T> list = new ArrayList<>();
            Map<String, String> map = new LinkedHashMap<>();
            Field[] fields = o.getClass().getDeclaredFields();
            while (resultSet.next()) {
                for (int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++) {
                    map.put(fields[i].getName(), resultSet.getString(i));
                }
                list.add(entityMapper.mapperInEntity(map));
            }
            return list;

    }
}
