package logic;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;


import java.io.FileReader;
import java.util.Map;
import java.util.Set;


/**
 * Created by michaelfolkmann on 12/11/2016.
 */
public class ConfigLoader {
    public static String SERVER_ADDRESS;
    public static String SERVER_PORT;
    public static String ENCRYPTION;
    public static String ENCRYPT_KEY;
    public static String HASH_SALT;

    private static final ConfigLoader SINGLETON = new ConfigLoader();

    public ConfigLoader getInstance() {
        return SINGLETON;
    }

    private ConfigLoader() {
        parseConfig();
    }

    /*Method that reads config.json and handles all importent key elements.
    * */
    public static void parseConfig() {
        JsonParser jparser = new JsonParser();
        JsonReader jsonReader;

        try {
            jsonReader = new JsonReader(new FileReader("config.json"));
            JsonObject jsonObject = jparser.parse(jsonReader).getAsJsonObject(); //opretter objekt af selve filen.

            Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();

            for (Map.Entry<String, JsonElement> entry : entries) {
                try {
                    ConfigLoader.class.getDeclaredField(entry.getKey()).set(SINGLETON, entry.getValue().getAsString());


                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


