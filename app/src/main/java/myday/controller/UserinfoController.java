package myday.controller;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for viewing user information.
 */
@Controller
public class UserinfoController {

	@RequestMapping("/userinfo") 
	public String welcome(Map<String, Object> model, OAuth2Authentication oauth) {
		if (oauth != null ) {
			ObjectMapper m = new ObjectMapper();
      Authentication auth = oauth.getUserAuthentication();
      
      @SuppressWarnings("unchecked")
      HashMap<String, Object> details  = m.convertValue(auth.getDetails(), HashMap.class);
			model.put("name", details.get("name"));
			model.put("picture_url", details.get("picture"));
		}
		return "userinfo";
	}

}
