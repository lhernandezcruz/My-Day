package myday.configuration;

import java.util.HashMap;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import myday.model.GoogleInfo;
import myday.model.GoogleInfoKey;
import myday.model.User;
import myday.repository.GoogleInfoRepository;
import myday.repository.UserRepository;

@Component
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {

  /**
   * CrudRepository for User
   */
  @Autowired
  private UserRepository userRep;

  /**
   * CrudRepository for GoogleInfo
   */
  @Autowired
  private GoogleInfoRepository infoRep;

  // turn into seperate successhandlers later so i can implement more than just google sign in
  @Override
  public void onApplicationEvent(AuthenticationSuccessEvent event) {
    OAuth2Authentication oauth = (OAuth2Authentication) event.getAuthentication();
    if (oauth != null) {
      ObjectMapper m = new ObjectMapper();
      Authentication auth = oauth.getUserAuthentication();
      
      @SuppressWarnings("unchecked")
      HashMap<String, Object> details  = m.convertValue(auth.getDetails(), HashMap.class);
      String sub = (String) details.get("sub"); 
      String name = (String) details.get("name");
      String picture = (String) details.get("picture");
      
      User user = fetchUser(sub);
      GoogleInfo googleInfo = fetchGoogleInfo(user, name, picture);

      System.out.println(user);
      System.out.println(googleInfo);
    }
    
    
  }


  /**
   * Fetches the User from database. Creates one if necessary.
   * 
   * @param sub - unique google identifier
   * @return - The User object corresponding to user.
   */
  private User fetchUser(String sub) {
    User user;
    Optional<User> userOpt = userRep.findBygoogleSub(sub);

    if (userOpt.isPresent()) {
      // user exists
      System.out.println("User already exists");
      user = userOpt.get();
      
    } else {
      // create user
      user = new User();
      user.setGoogleSub(sub);
      userRep.save(user);
    }
    return user;
  }


  /**
   * Fetches the GoogleInfo from database. Creates info if necessary.
   * 
   * @param user - User object
   * @param name - name of the user
   * @param picture - picture url for profile pic
   * @return - The GoogleInfo object corresponding to user.
   */
  private GoogleInfo fetchGoogleInfo(User user, String name, String picture) {
    GoogleInfo googleInfo;
    Optional<GoogleInfo> googleInfoOpt = infoRep.findById(new GoogleInfoKey(user));

    if (googleInfoOpt.isPresent()) {
      // google info exists
      System.out.println("Google info already exists");

      googleInfo = googleInfoOpt.get();
    } else {
      System.out.println("Creating google info");

      googleInfo = new GoogleInfo(user, name, picture);
      infoRep.save(googleInfo);
    }
    return googleInfo;
  }


}