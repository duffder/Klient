
import com.google.gson.Gson;

import logic.ConfigLoader;
import logic.mainController;
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

    //Run er også vores EntryPoint i systemet.



    public static void main(String[] args) {


        /*
        * First we initialize the Config file and then controller takes over
        * */
        ConfigLoader.parseConfig();
        mainController MainController = new mainController();
        System.out.println("Welcome to the rating system!");
        MainController.Controller();



    }

}
