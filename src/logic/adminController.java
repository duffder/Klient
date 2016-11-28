package logic;

import sdk.ConnectionHandler;
import sdk.connection.ResponseCallback;
import sdk.models.AccessService;
import sdk.models.Review;


import java.util.Scanner;

/**
 * Created by michaelfolkmann on 22/11/2016.
 */
public class adminController {
    Scanner input = new Scanner(System.in);
    ConnectionHandler connectionHandler = new ConnectionHandler();




    //Do not work perfectly due to server complications.
    public void deleteReview(){
        Review review = new Review();

        System.out.println("Lecture ID: ");
        int lectureID = input.nextInt();

        review.setId(lectureID);
        review.setUserId(AccessService.getAccessToken().getId());
        System.out.println(review.getUserId());

        connectionHandler.updateReview(review, new ResponseCallback<Boolean>() {
            public void succes(Boolean data) {
                if (data = true) {
                    System.out.println("Review is deleted");

                }


            }

            public void error(int status) {
                System.out.println(status);

            }
        });


    }

}
