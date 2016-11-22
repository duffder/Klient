package view;

import logic.mainController;

import java.util.Scanner;

/**
 * Created by michaelfolkmann on 17/11/2016.
 */
public class mainView {

    Scanner input = new Scanner(System.in);


    public void mainMenu() {
        mainController MainController = new mainController();


        System.out.println("Main menu: ");
        System.out.println("1: Login");
        System.out.println("2: Afslut program");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                MainController.Login();

                break;
            case 2:


                break;
            case 3:

                break;

            default:

                break;

        }
    }
}
