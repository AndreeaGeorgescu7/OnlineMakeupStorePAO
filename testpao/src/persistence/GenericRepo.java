package persistence;

public interface GenericRepo<T, S> {

    public void add(T entity);

    public T get(int id);

    public void update(T entity, S upd);

    public void delete(T entity);


    public int getSize();

    public int getNumberOf();
}
