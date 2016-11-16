package sdk.connection;

/**
 * Created by michaelfolkmann on 14/11/2016.
 */
public interface ResponseCallback<T> {
    void succes(T data);
    void error(int status);
}
