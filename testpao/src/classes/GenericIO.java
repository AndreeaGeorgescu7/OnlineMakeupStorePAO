package classes;

import java.io.IOException;
import java.util.List;

///not used
public interface GenericIO <T>{

    public  List<T> readEntity(String fileName) throws IOException;

    public void writeEntity(String fileName,T entity) throws IOException;

    public boolean hasReachedEnd();

    public void close() throws IOException;

}