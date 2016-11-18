
import com.google.gson.Gson;

import sdk.ConnectionHandler;
import sdk.connection.ResponseCallback;
import sdk.models.AccessService;
import sdk.models.Lectures;
import sdk.models.User;
import sercurity.Digester;
import javax.ws.rs.core.Response;


import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by michaelfolkmann on 09/11/2016.
 */
public class Run {



    public static void main(String[] args) {

        ConnectionHandler connectionHandler = new ConnectionHandler();

        String password = "tet";
        String securedPassword;
        securedPassword = Digester.hashWithSalt(password);


        connectionHandler.authLogin("test", securedPassword, new ResponseCallback<User>() {
            public void succes(User data) {
                try {
                    if (data == null){
                        System.out.println("Wrong login or password!");
                    }else {
                        System.out.println("Virker");
                        System.out.println(data.getId());
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                }


            }

            public void error(int status) {
                System.out.println("virker ikke");

            }
        });


/*
        connectionHandler.getLectures("BALJO1001U_LA_E16", new ResponseCallback<ArrayList<Lectures>>() {
            public void succes(ArrayList<Lectures> data) {
                System.out.println("Virker");
                for (Lectures allLectures:data) {
                    System.out.println("Discription: " + allLectures.getDescription());
                    System.out.println("Type: " + allLectures.getType());
                    System.out.println("Starts at: " + allLectures.getStartDate());
                    System.out.println("And will ends at: " + allLectures.getEndDate());
                }
            }

            public void error(int status) {
                System.out.println("Virker ikke");
                System.out.println(status);

            }
        });
*/




    }

}
