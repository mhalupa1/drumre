package hr.fer.drumre.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthData {

    public OAuthData(String oAuthToken, String oAuthVerifier){
        this.oAuthToken = oAuthToken;
        this.oAuthVerifier = oAuthVerifier;
    }
    private String oAuthToken;
    private String oAuthVerifier;
}
