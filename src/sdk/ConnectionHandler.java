package sdk;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import sdk.connection.Connection;
import sdk.connection.ResponseCallback;
import sdk.connection.ResponseParser;
import sdk.models.AccessService;
import sdk.models.Course;
import sdk.models.Lectures;
import sdk.models.User;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


/**
 * Created by michaelfolkmann on 14/11/2016.
 */
public class ConnectionHandler {

    private Connection connection;
    private Gson gson;
    private AccessService accessService;

    public ConnectionHandler(){
        this.connection = new Connection();
        this.gson = new Gson();
        this.accessService = new AccessService();
    }

    public void authLogin(String cbsMail, String password , final ResponseCallback<User> responseCallback){
        HttpPost postRequest = new HttpPost(Connection.serverURL + "/login");
        final User userInfo = new User();
        userInfo.setCbsMail(cbsMail);
        userInfo.setPassword(password);

        try {
            StringEntity loginInfo = new StringEntity(this.gson.toJson(userInfo));
            postRequest.setEntity(loginInfo);
            postRequest.setHeader("Content-Type", "application/json");

            this.connection.execute(postRequest, new ResponseParser() {
                public void payload(String json) {
                    User userToken = gson.fromJson(json, User.class);
                    AccessService.setAccessToken(userToken);
                    responseCallback.succes(userToken);
                }

                public void error(int status) {
                    responseCallback.error(status);


                }
            });
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }


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
