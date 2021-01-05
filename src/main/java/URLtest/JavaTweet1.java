package URLtest;

import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.util.ArrayList;
import java.util.List;

public class JavaTweet1 {

    static String consumerKeyStr = "eVnK6Cdp4HZ65wrHOJ8Zv4kXd";
    static String consumerSecretStr = "lFRAdgGJs0HjanVgMhmVPRqp4HZ7VGajVo5kaWBpqwtStIJUId";
    static String accessTokenStr = "2420490421-RZvTgB6JNZ9xhVknZ6tC6qOWwLKra8HfxjapJKw";
    static String accessTokenSecretStr = "5AQr0qEVtuPzkcFCUZmqnxFNtD7m6C2v7vxLzFSELcGTC";

    public static void main(String[] args) {

        try {
            Twitter twitter = new TwitterFactory().getInstance();

            twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
            AccessToken accessToken = new AccessToken(accessTokenStr,
                    accessTokenSecretStr);

            twitter.setOAuthAccessToken(accessToken);
            PagableResponseList<User> friends = twitter.friendsFollowers().getFriendsList(2420490421L, -1);
            System.out.println("Length of friends: " + friends.size());
            /*
            for (User u : friends){
                System.out.println("Friend name: " + u.getName() + "  screenName: " + u.getScreenName() + " location: " + u.getLocation() + u.get);
            }
            PagableResponseList<User> followers = twitter.friendsFollowers().getFollowersList(2420490421L, -1);
            System.out.println("Length of friends: " + followers.size());
            for (User u : followers){
                System.out.println("Follower name: " + u.getName() + "  screenName: " + u.getScreenName() + " location: " + u.getLocation() );
            }
            System.out.println("SCreenName: " + twitter.getScreenName() + " ID: " + twitter.getId());

             */
            Twitter twitter2 = new TwitterFactory().getInstance();
            twitter2.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
            twitter2.setOAuthAccessToken(accessToken);
            GeoLocation zonaNorte = new GeoLocation(-34.497620,-58.497689);
            //Query query = new Query("ricardo iorio");
            Query query = new Query();
            query.geoCode(zonaNorte, 10, Query.Unit.km);
            QueryResult result = twitter2.search(query);
            for (Status status : result.getTweets()) {
                System.out.println("@" + status.getUser().getScreenName()  + " (ID: " + status.getUser().getId() + ") email= " + status.getUser().getEmail()+ ":" + status.getText());
            }

        } catch (Exception te) {
            te.printStackTrace();
        }
    }

}
