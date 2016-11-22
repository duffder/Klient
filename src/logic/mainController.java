package logic;


import sdk.ConnectionHandler;
import sdk.connection.ResponseCallback;
import sdk.models.AccessService;
import sdk.models.User;
import sercurity.Digester;
import view.mainView;
import view.studentView;

import java.security.PublicKey;
import java.util.Scanner;

/**
 * Created by michaelfolkmann on 18/11/2016.
 */
public class mainController {
    Scanner input = new Scanner(System.in);
    ConnectionHandler connectionHandler = new ConnectionHandler();


    public void Controller(){
        mainView view = new mainView();

        view.mainMenu();

    }


    public void Login(){
        final studentView StudentView = new studentView();
        System.out.println("CBS-Email: ");
        String cbsMail = input.nextLine();
        System.out.println("Password: ");
        String password = input.nextLine();

        //Hashing of password is here.
        String securedPassword;
        securedPassword = Digester.hashWithSalt(password);


        connectionHandler.authLogin(cbsMail, securedPassword, new ResponseCallback<User>() {
            public void succes(User data) {
                try {
                    if (data == null){
                        System.out.println("Wrong email or password!");
                    }else {
                        System.out.println("Login information: ");
                        System.out.println("ID: " + data.getId());
                        System.out.println("Type: " + data.getType());
                        System.out.println();
                        StudentView.studentMenu();
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                }


            }

            public void error(int status) {
                System.out.println("virker ikke");

            }
        });
    }
    public static void logout(){
        AccessService.clear();
    }
    public static User current(){
        return AccessService.getAccessToken().getUser();

    }

}
