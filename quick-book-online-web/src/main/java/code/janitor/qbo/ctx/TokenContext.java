package code.janitor.qbo.ctx;
import java.util.HashMap;

import org.springframework.stereotype.Component;

@SuppressWarnings("all")
@Component
public class TokenContext extends HashMap<String,AuthToken> {

	private static final long serialVersionUID = -4703175562209072061L;
	
	private static TokenContext INSTANCE = new TokenContext();
    private TokenContext() {}
    

    public static TokenContext getInstance() {return INSTANCE;}
    
    public AuthToken getToken(String key) 
    {
    	return INSTANCE.get(key);
    	
    }
}