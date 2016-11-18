package view;

import java.util.Scanner;

/**
 * Created by michaelfolkmann on 17/11/2016.
 */
public class mainView {

    Scanner input = new Scanner(System.in);

    public void mainMenu() {

        int mainMenu = 8;

        switch (mainMenu) {
            case 1:
                System.out.println("1: Login");
                break;
            case 2:
                System.out.println("2: Afslut program");

                break;
            case 3:

                break;

            default:

                break;

        }
    }
}
