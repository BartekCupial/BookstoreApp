package kernel;

import records.Book;

import java.util.List;

public class RecordAdapter implements Record{
    @Override
    public void insert(AbstractRecord record) {

    }

    @Override
    public AbstractRecord selectById(int id) {
        return null;
    }

    @Override
    public AbstractRecord selectById(String id) {
        return null;
    }

    @Override
    public List<AbstractRecord> selectAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(AbstractRecord record, int id) {

    }

    @Override
    public void update(AbstractRecord record, String id) {

    }

    @Override
    public void update(AbstractRecord record) {

    }

    @Override
    public int insertint(AbstractRecord record){
        return 0;
    }




}
