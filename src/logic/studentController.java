package logic;

import com.sun.org.apache.regexp.internal.RE;
import sdk.ConnectionHandler;
import sdk.connection.ResponseCallback;
import sdk.models.AccessService;
import sdk.models.Course;
import sdk.models.Lectures;
import sdk.models.Review;
import view.studentView;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by michaelfolkmann on 18/11/2016.
 */
public class studentController {
    Scanner input = new Scanner(System.in);
    studentView StudentView = new studentView();
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
                StudentView.studentMenu();

            }

            public void error(int status) {
                System.out.println(status);

            }
        });


    }

    /*addReviews handles the process of adding a review to the system.
    * It uses the users AccessToken to locale the user ID.*/

    public void addReview(){
        Review review = new Review();
        review.setUserId(AccessService.getAccessToken().getId());

        System.out.println("Lecture ID: ");
        final int LectureID = input.nextInt();
        review.setLectureId(LectureID);

        System.out.println("Rating 0-5: ");
        int Rating = input.nextInt();
        review.setRating(Rating);

        System.out.println("Comment: ");
        String Comment = input.next();
        review.setComment(Comment);

        connectionHandler.addReview(review, new ResponseCallback<String>() {
            public void succes(String data) {
            System.out.println("You have added your review!");
                System.out.println("Lecture ID: " + LectureID);
                System.out.println("Thank you.");
                System.out.println();
                StudentView.studentMenu();
            }

            public void error(int status) {

            }
        });

    }
//Due to the fact that the User don't know the actual primary review ID in the DB, I have changed
    //the the DB endpoint pointer on server from "id" to "lecture_id", that the user will know from lectures.
    public void getReviews(){
        System.out.println("Lecture ID: ");
       int id = input.nextInt();
        System.out.println();

        connectionHandler.getReview(id, new ResponseCallback<ArrayList<Review>>() {
            public void succes(ArrayList<Review> data) {
                try {
                    for (Review allReview : data) {
                        System.out.println("ID: " + allReview.getId());
                        System.out.println("Rating: " + allReview.getRating());
                        System.out.println("Comment: " + allReview.getComment());
                    }
                }catch (NullPointerException e){
                    System.out.println("Not reviewed!");
                }
                StudentView.studentMenu();
            }

            public void error(int status) {
                System.out.println("No review");
                StudentView.studentMenu();

            }
        });



    }

    public void getCourses(){

        connectionHandler.getCourses(AccessService.getAccessToken().getId(), new ResponseCallback<ArrayList<Course>>() {
            public void succes(ArrayList<Course> data) {

                ArrayList<Course> coursearray = new ArrayList<Course>();

                for (Course course: data) {
                    Course courses = new Course();

                    courses.setDisplaytext(course.getDisplaytext());

                    System.out.println(course.getDisplaytext());

                    coursearray.add(courses);
                }

                for (Course courseall:coursearray) {
                    connectionHandler.getLectures(courseall.getDisplaytext(), new ResponseCallback<ArrayList<Lectures>>() {
                        //On server: Added lecture ID to the UserController in getLectures so we can show
                        //the lecture ID.
                        public void succes(ArrayList<Lectures> data) {
                            for (Lectures allLectures:data){
                                System.out.println("Lecture ID: " + allLectures.getId());
                                System.out.println("Discription: " + allLectures.getDescription());
                                System.out.println("Type: " + allLectures.getType());
                                System.out.println("Starts at: " + allLectures.getStartDate());
                                System.out.println("And will ends at: " + allLectures.getEndDate());
                                System.out.println();
                            }
                            StudentView.studentMenu();
                        }

                        public void error(int status) {

                        }
                    });
                }
            }

            public void error(int status) {

            }
        });
    }


}
