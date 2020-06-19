package code.janitor.qbo.rest;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import code.janitor.qbo.ctx.AuthToken;
import code.janitor.qbo.ctx.TokenContext;

@RestController
public class TokenService {
	private static final Logger logger = Logger.getLogger(TokenService.class);
	
	
	
	@GetMapping(path="/token/aquire" ,produces=MediaType.APPLICATION_JSON_VALUE)
	public AuthToken retrieveToken() {
		logger.debug(">>>> returning token "+ TokenContext.getInstance().get("token"));
		return  TokenContext.getInstance().get("token");
	}

}
