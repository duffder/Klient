package logic;


import sdk.ConnectionHandler;
import sdk.connection.ResponseCallback;
import sdk.models.AccessService;
import sdk.models.User;
import sercurity.Digester;

import view.adminView;
import view.mainView;
import view.studentView;


import java.util.Scanner;

/**
 * Created by michaelfolkmann on 18/11/2016.
 */
public class mainController {
    Scanner input = new Scanner(System.in);
    ConnectionHandler connectionHandler = new ConnectionHandler();
    mainView view = new mainView();
    adminView AdminView = new adminView();



    public void Controller(){


        view.mainMenu();

    }

    /*Handles login for all types of users in the system.*/
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
                        view.mainMenu();
                    }else if (data.getType().equals("student")){
                        System.out.println("Login information: ");
                        System.out.println("ID: " + data.getId());
                        System.out.println("Type: " + data.getType());
                        System.out.println();
                        StudentView.studentMenu();
                    }else if (data.getType().equals("admin")) {
                        System.out.println("Login information: ");
                        System.out.println("ID: " + data.getId());
                        System.out.println("Type: " + data.getType());
                        System.out.println();
                        AdminView.adminMenu();
                    }

                }catch (NullPointerException e){
                    e.printStackTrace();
                }


            }

            public void error(int status) {
                System.out.println("virker ikke");
                view.mainMenu();

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
