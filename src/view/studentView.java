package view;

import logic.mainController;
import logic.studentController;


import java.util.Scanner;

/**
 * Created by michaelfolkmann on 16/11/2016.
 */
public class studentView {
    Scanner input = new Scanner(System.in);

    public void studentMenu(){
        studentController studentController = new studentController();
        mainView MainView = new mainView();
        mainController MainController = new mainController();


        System.out.println("1: Rate lecture");
        System.out.println("2: Show reviews");
        System.out.println("3: Show lectures");
        System.out.println("4: Show participating");
        System.out.println("5: Delete own comment");
        System.out.println("6: Logout");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                studentController.addReview();
                break;
            case 2:
                studentController.getReviews();
                break;
            case 3:
                studentController.getCourses();
                break;
            case 4:

                break;
            case 5:
                studentController.deleteReview();
                break;
            case 6:
                System.out.println("Logging out!");
                MainView.mainMenu();
                MainController.logout();


            default:

                break;

        }


    }
}
