package sdk.models;

/**
 * Created by michaelfolkmann on 16/11/2016.
 */
public class User {
    private int id;
    private String cbsMail, password, type;
    private User user;

    public User(int id, String cbsMail, String password, String type) {
        this.id = id;
        this.cbsMail = cbsMail;
        this.password = password;
        this.type = type;
    }

public User(){

}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCbsMail() {
        return cbsMail;
    }

    public void setCbsMail(String cbsMail) {
        this.cbsMail = cbsMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
