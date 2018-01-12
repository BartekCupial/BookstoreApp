package kernel;

import java.util.List;

public interface Record {

    void insert(AbstractRecord record);

    int insertint(AbstractRecord record);

    AbstractRecord selectById(int id);

    AbstractRecord selectById(String id);

    List<AbstractRecord> selectAll();

    void delete(int id);

    void delete(String id);

    void update(AbstractRecord record, int id);

    void update(AbstractRecord record, String id);

    void update(AbstractRecord record);

}
