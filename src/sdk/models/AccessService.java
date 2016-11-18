package sdk.models;

/**
 * Created by michaelfolkmann on 17/11/2016.
 */
public class AccessService {
    private static User accessToken;

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
