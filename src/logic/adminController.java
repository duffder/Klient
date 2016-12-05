package logic;

import sdk.ConnectionHandler;
import sdk.connection.ResponseCallback;
import sdk.models.Review;
import view.adminView;
import view.mainView;


import java.util.Scanner;

/**
 * Created by michaelfolkmann on 22/11/2016.
 */
public class adminController {
    Scanner input = new Scanner(System.in);
    ConnectionHandler connectionHandler = new ConnectionHandler();
    mainView view = new mainView();




    //Do not work perfectly due to server complications.
    public void deleteReview(){
        Review review = new Review();

        System.out.println("User ID: ");
        int userID = input.nextInt();
        System.out.println("Lecture ID: ");
        int lectureID = input.nextInt();

        review.setUserId(userID);
        review.setId(lectureID);

        connectionHandler.updateReview(review, new ResponseCallback<Boolean>() {
            public void succes(Boolean data) {
                if (data = true) {
                    System.out.println("Review is deleted");
                }
                view.mainMenu();


            }

            public void error(int status) {
                System.out.println(status);

            }
        });


    }

}
