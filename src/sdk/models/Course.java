package sdk.models;

/**
 * Created by michaelfolkmann on 16/11/2016.
 */
public class Course {


    private String id;
    private String code;
    private String displaytext;

    public Course(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplaytext() {
        return displaytext;
    }

    public void setDisplaytext(String displaytext) {
        this.displaytext = displaytext;
    }
}
