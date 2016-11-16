package sdk;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpGet;
import sdk.connection.Connection;
import sdk.connection.ResponseCallback;
import sdk.connection.ResponseParser;
import sdk.models.Course;
import sdk.models.Lectures;

import java.util.ArrayList;


/**
 * Created by michaelfolkmann on 14/11/2016.
 */
public class ConnectionHandler {

    private Connection connection;
    private Gson gson;

    public ConnectionHandler(){
        this.connection = new Connection();
        this.gson = new Gson();
    }

    public void getCourses(String userId, final ResponseCallback<ArrayList<Course>> responseCallback) {
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/review/" + userId);
        this.connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {
                ArrayList<Course> courses = gson.fromJson(json, new TypeToken<ArrayList<Course>>(){}.getType());
                responseCallback.succes(courses);
            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });
    }

    public void getLectures(String code, final ResponseCallback<ArrayList<Lectures>> responseCallback) {
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/lecture/" + code);
        this.connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {
                ArrayList<Lectures> lectures = gson.fromJson(json, new TypeToken<ArrayList<Lectures>>(){}.getType());
                responseCallback.succes(lectures);
            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });
    }
}
