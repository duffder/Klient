package sdk.models;

/**
 * Created by michaelfolkmann on 17/11/2016.
 */
public class AccessService {
    private static User accessToken;

    //This class sets the user as an AccessToken and gather
    //informations on a specific user in the system.

    public static void setAccessToken(User token){

        accessToken = token;
    }
    public static User getAccessToken(){

        return accessToken;
    }
    public static void clear(){

        accessToken = null;
    }
}
