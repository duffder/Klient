package sdk;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import sdk.connection.Connection;
import sdk.connection.ResponseCallback;
import sdk.connection.ResponseParser;
import sdk.models.*;

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

    public void updateReview(Review review, final ResponseCallback<Boolean> responseCallback) {

        try {
            HttpPut putRequest = new HttpPut(Connection.serverURL + "/student/review/");

            putRequest.addHeader("Content-Type", "application/json");

            StringEntity jsonReview = new StringEntity(gson.toJson(review));
            putRequest.setEntity(jsonReview);

            connection.execute(putRequest, new ResponseParser() {
                public void payload(String json) {
                    Boolean isDeleted = gson.fromJson(json, Boolean.class);
                    responseCallback.succes(isDeleted);

                }

                public void error(int status) {
                    responseCallback.error(status);

                }
            });
        }catch (Exception e){

        }
    }

    public void addReview(Review review, final ResponseCallback<String> responseCallback ) {
        HttpPost postRequest = new HttpPost(Connection.serverURL + "/student/review");


        try {
            StringEntity reviewObject = new StringEntity(this.gson.toJson(review));
            postRequest.setEntity(reviewObject);
            postRequest.setHeader("Content-Type", "application/json");

            this.connection.execute(postRequest, new ResponseParser() {
                public void payload(String json) {
                    String addedReview = gson.fromJson(json, String.class);
                    responseCallback.succes(addedReview);
                }

                public void error(int status) {
                    responseCallback.error(status);
                }
            });
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    public void getReview(int courseId, final ResponseCallback<ArrayList<Review>> responseCallback) {
        HttpGet getRquest = new HttpGet(Connection.serverURL + "/review/" + courseId);
        this.connection.execute(getRquest, new ResponseParser() {
            public void payload(String json) {
                ArrayList<Review> reviews = gson.fromJson(json, new TypeToken<ArrayList<Review>>(){}.getType());
                responseCallback.succes(reviews);
            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });
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


    public void getCourses(int userId, final ResponseCallback<ArrayList<Course>> responseCallback) {
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/course/" + userId);
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
