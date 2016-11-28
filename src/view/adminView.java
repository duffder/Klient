package view;

import logic.adminController;
import sdk.models.AccessService;

import java.util.Scanner;

/**
 * Created by michaelfolkmann on 22/11/2016.
 */
public class adminView {

    Scanner input = new Scanner(System.in);
    adminController AdminController = new adminController();

    mainView MainView = new mainView();

    public void adminMenu() {

        int adminMenu = 3;

        System.out.println("1: Delete comment");
        System.out.println("2: Logout");
        adminMenu = input.nextInt();

        switch (adminMenu) {
            case 1:
                AdminController.deleteReview();
                break;
            case 2:
                MainView.mainMenu();
                AccessService.clear();
                break;

            default:

                break;

        }
    }
}
