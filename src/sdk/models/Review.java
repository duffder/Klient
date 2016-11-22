package sdk.models;

/**
 * Created by michaelfolkmann on 21/11/2016.
 */
public class Review {

    private int id;
    private int userId;
    private int lectureId;
    private int rating;
    private String comment;
    private boolean isDeleted;

    public Review(int id, int userId, int lectureId, int rating, String comment, boolean isDeleted) {
        this.id = id;
        this.userId = userId;
        this.lectureId = lectureId;
        this.rating = rating;
        this.comment = comment;
        this.isDeleted = isDeleted;
    }

    public Review(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
