
import com.google.gson.Gson;

import sdk.ConnectionHandler;
import sdk.connection.ResponseCallback;
import sdk.models.Lectures;
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

        Lectures LecturesToShow = new Lectures();

        connectionHandler.getLectures("BALJO1001U_LA_E16", new ResponseCallback<ArrayList<Lectures>>() {
            public void succes(ArrayList<Lectures> data) {
                System.out.println("Virker");
                for (Lectures allLectures:data) {
                    System.out.println("ID: " + allLectures.getId());
                    System.out.println("Discription: " + allLectures.getDescription());
                    System.out.println("Type: " + allLectures.getType());
                    System.out.println("Starts at: " + allLectures.getStartDate());
                }
            }

            public void error(int status) {
                System.out.println("Virker ikke");
                System.out.println(status);

            }
        });





    }

}
