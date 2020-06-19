package code.janitor.qbo.web.cntlr;

import static code.janitor.qbo.web.AppConstant.REDIRECT_URI;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.intuit.oauth2.exception.OAuthException;

import code.janitor.qbo.ctx.AuthToken;
import code.janitor.qbo.ctx.TokenContext;
import code.janitor.qbo.web.client.OAuth2PlatformClientFactory;

@Controller
public class CallbackController {
	
	
	
	@Autowired
	OAuth2PlatformClientFactory factory;
	

    private static final Logger logger = Logger.getLogger(CallbackController.class);
    
    /**
     *  This is the redirect handler you configure in your app on developer.intuit.com
     *  The Authorization code has a short lifetime.
     *  Hence Unless a user action is quick and mandatory, proceed to exchange the Authorization Code for
     *  BearerToken
     *      
     * @param auth_code
     * @param state
     * @param realmId
     * @param session
     * @return
     */
    @RequestMapping("/oauth2redirect")
    public String callBackFromOAuth(@RequestParam("code") String authCode, @RequestParam("state") String state, @RequestParam(value = "realmId", required = false) String realmId, HttpSession session) {   
        
        try {
	        String csrfToken = (String) session.getAttribute("csrfToken");
	        if (csrfToken.equals(state)) {
	        	
	        	/*session.setAttribute("realmId", realmId);
	            session.setAttribute("auth_code", authCode);*/
	
	            OAuth2PlatformClient client  = factory.getOAuth2PlatformClient();
	            String redirectUri = factory.getPropertyValue(REDIRECT_URI);
	            
	            BearerTokenResponse bearerTokenResponse = client.retrieveBearerTokens(authCode, redirectUri);
	            
	            AuthToken token = new AuthToken();
	            token.setRealmId(realmId);
	            token.setAuthCode(authCode);
	            token.setAccessToken(bearerTokenResponse.getAccessToken());
	            token.setRefreshToken(bearerTokenResponse.getRefreshToken());
	        	 
	            /*session.setAttribute("access_token", bearerTokenResponse.getAccessToken());
	            session.setAttribute("refresh_token", bearerTokenResponse.getRefreshToken());*/
	    
	            
	            TokenContext.getInstance().put("token",token);
	            
	            // Update your Data store here with user's AccessToken and RefreshToken along with the realmId

	            return "connected";
	        }
	        
	        logger.info("CSRF mismatch >> Cannot move ahead..." );
        
        } catch (OAuthException e) {
        	logger.error("Exception in callback handler ", e);
		} 
        return null;
    }


}
