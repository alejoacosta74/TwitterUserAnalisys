package URLtest;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class test1 {

    public static HttpResponse executeGet(
            HttpTransport transport, JsonFactory jsonFactory, String accessToken, GenericUrl url)
            throws IOException {
        Credential credential =
                new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
        HttpRequestFactory requestFactory = transport.createRequestFactory(credential);
        return requestFactory.buildGetRequest(url).execute();
    }



    public static void main(String[] args) {

        String url = "https://api.twitter.com/labs/2/users/by?usernames=twitterdev&user.fields=created_at,description,pinned_tweet_id";

        try {

            HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
            HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(url));
            String rawResponse = request.execute().parseAsString();
            System.out.println("Raw response" + rawResponse);
        }
        catch (Exception e){
            System.out.println("what the fuck");
            e.printStackTrace();

        }

    }
}
