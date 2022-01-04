package hr.fer.drumre.service;

import hr.fer.drumre.dao.UserRepository;
import hr.fer.drumre.model.OAuthData;
import hr.fer.drumre.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Autowired
    UserRepository repo;

    String twitterUrl = "";
    RequestToken requestToken;
    public String login() throws TwitterException {
        Twitter twitter = getTwitter();

        requestToken = twitter.getOAuthRequestToken("http://127.0.0.1:8080/user.html");
        //now get the authorization URL from the token
        twitterUrl = requestToken.getAuthorizationURL();

        return twitterUrl;
    }

    private Twitter getTwitter(){
        Twitter twitter = null;
        String apiKey = "tJdy3tyadS5Kahwh476yRfZvM";
        String apiSecret = "ySh1xueQIkn18B3x8AS5LjrDZCFZNUslBNsExf3gG4jAR3oTkl";

        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(apiKey);
        builder.setOAuthConsumerSecret(apiSecret);
        var configuration = builder.build();

        //instantiate the Twitter object with the configuration
        TwitterFactory factory = new TwitterFactory(configuration);
        twitter = factory.getInstance();


        return twitter;

    }

    public AccessToken getTwitterCallback(OAuthData data) throws TwitterException {
        Twitter twitter = getTwitter();
        AccessToken token = twitter.getOAuthAccessToken(requestToken, data.getoAuthVerifier());
        repo.save(new User(String.valueOf(token.getUserId()),token.getScreenName()));
        return token;
    }

}
