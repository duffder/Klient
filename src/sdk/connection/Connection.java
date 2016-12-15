package sdk.connection;

import logic.ConfigLoader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by michaelfolkmann on 14/11/2016.
 */
public class Connection {

    public static String serverURL = "http://" + ConfigLoader.SERVER_ADDRESS + ":" + ConfigLoader.SERVER_PORT + "/api";
    private CloseableHttpClient httpClient;

    /*This class handles all general connections to the server.
    * It uses the ResponseParser interface, to parse the response.
     * */

    public Connection() {
        this.httpClient = HttpClients.createDefault();
    }

    public void execute(HttpUriRequest uriRequest, final ResponseParser parser){

        // Creates a custom response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            public String handleResponse(final HttpResponse response) throws IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    parser.error(status);
                }
                return null;
            }

        };

        try {
            String json = this.httpClient.execute(uriRequest, responseHandler);

            parser.payload(json);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
