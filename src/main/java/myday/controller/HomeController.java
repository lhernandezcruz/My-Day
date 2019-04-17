package myday.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the home page
 */
@EnableOAuth2Client
@Controller
public class HomeController {
	@Autowired
  OAuth2ClientContext oauth2ClientContext; // look up OAuth2ClientContext

	@RequestMapping("/user")
  public Principal user(Principal principal) {
    return principal;
	}
	@RequestMapping("/") 
	public String welcome(Map<String, Object> model, OAuth2Authentication oauth) {
		if (oauth != null ) {
			ObjectMapper m = new ObjectMapper();
      Authentication auth = oauth.getUserAuthentication();
      
      @SuppressWarnings("unchecked")
      HashMap<String, Object> details  = m.convertValue(auth.getDetails(), HashMap.class);
			model.put("name", details.get("name"));
			model.put("picture_url", details.get("picture"));
		}
		return "index";
	}

}

