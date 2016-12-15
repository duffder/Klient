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
import sercurity.Digester;

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

    /*This class handles all the requests to the endpoints on the server.
    * In general I use the responseCallback to send the gathered information which
    * is then executed and handled in the payload method.
    * */

    public void updateReview(Review review, final ResponseCallback<Boolean> responseCallback) {

        try {
            HttpPut putRequest = new HttpPut(Connection.serverURL + "/student/review/");

            putRequest.addHeader("Content-Type", "application/json");

            StringEntity jsonReview = new StringEntity(gson.toJson(review));
            putRequest.setEntity(jsonReview);

            connection.execute(putRequest, new ResponseParser() {
                public void payload(String json) {
                    String decrypted = Digester.decrypt(json);
                    Boolean isDeleted = gson.fromJson(decrypted, Boolean.class);
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
                    String decrypted = Digester.decrypt(json);
                    String addedReview = gson.fromJson(decrypted, String.class);
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
                String decrypted = Digester.decrypt(json);
                ArrayList<Review> reviews = gson.fromJson(decrypted, new TypeToken<ArrayList<Review>>(){}.getType());
                responseCallback.succes(reviews);
            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });
    }

    //Importent method which also handles the hashed password and aswell encrypt it
    //before send to the server.
    public void authLogin(String cbsMail, String password , final ResponseCallback<User> responseCallback){
        HttpPost postRequest = new HttpPost(Connection.serverURL + "/login");
        final User userInfo = new User();
        String encryptedMail = Digester.encrypt(cbsMail);
        userInfo.setCbsMail(encryptedMail);
        String encryptedPassword = Digester.encrypt(password);
        userInfo.setPassword(encryptedPassword);

        try {
            StringEntity loginInfo = new StringEntity(this.gson.toJson(userInfo));
            postRequest.setEntity(loginInfo);
            postRequest.setHeader("Content-Type", "application/json");

            this.connection.execute(postRequest, new ResponseParser() {
                public void payload(String json) {
                    String decrypted = Digester.decrypt(json);
                    User userToken = gson.fromJson(decrypted, User.class);
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
                String decrypted = Digester.decrypt(json);
                ArrayList<Course> courses = gson.fromJson(decrypted, new TypeToken<ArrayList<Course>>(){}.getType());
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
                String decrypted = Digester.decrypt(json);
                ArrayList<Lectures> lectures = gson.fromJson(decrypted, new TypeToken<ArrayList<Lectures>>(){}.getType());
                responseCallback.succes(lectures);
            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });
    }


}
