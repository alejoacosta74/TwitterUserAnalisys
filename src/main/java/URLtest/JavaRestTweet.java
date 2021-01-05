package URLtest;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

public class JavaRestTweet {
    static String consumerKeyStr = "eVnK6Cdp4HZ65wrHOJ8Zv4kXd";
    static String consumerSecretStr = "lFRAdgGJs0HjanVgMhmVPRqp4HZ7VGajVo5kaWBpqwtStIJUId";
    static String accessTokenStr = "2420490421-RZvTgB6JNZ9xhVknZ6tC6qOWwLKra8HfxjapJKw";
    static String accessTokenSecretStr = "5AQr0qEVtuPzkcFCUZmqnxFNtD7m6C2v7vxLzFSELcGTC";


    public static void main(String[] args) throws Exception {
        OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKeyStr,consumerSecretStr);
        oAuthConsumer.setTokenWithSecret(accessTokenStr, accessTokenSecretStr);

        HttpPost httpPost = new HttpPost(
                "http://api.twitter.com/1.1/statuses/update.json?status=Hello%20Twitter%20World.");

        oAuthConsumer.sign(httpPost);
        //HttpClient httpClient = new DefaultHttpClient();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://api.twitter.com/2/users?ids=2420490421");
        oAuthConsumer.sign(httpGet);
        HttpResponse httpResponse = httpClient.execute(httpGet);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode + ':' + httpResponse.getStatusLine().getReasonPhrase());
        System.out.println(IOUtils.toString(httpResponse.getEntity().getContent()));

    }

}

