package myday.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import myday.model.Rating;
import myday.model.User;
import myday.model.ViewedRating;
import myday.repository.RatingsRepository;
import myday.repository.UserRepository;

/**
 * Controller for submitting ratings
 */
@Controller
public class RatingsController {
  /**
   * CRUD repository for user
   */
  @Autowired
  private UserRepository userRepository;

  /**
   * CRUD repository for ratings
   */
  @Autowired
  private RatingsRepository ratingsRepository;


  /**
   * Receives get request for new ratings
   */
  @GetMapping("/view/all")
  public String viewAll(Model model, OAuth2Authentication oauth) {
    // insert NewRating that will be populated by form
    Authentication auth = oauth.getUserAuthentication();
    ObjectMapper m = new ObjectMapper();

    @SuppressWarnings("unchecked")
    HashMap<String, Object> details  = m.convertValue(auth.getDetails(), HashMap.class);

    User user = fetchUser((String) details.get("sub"));
    ArrayList<ViewedRating> ratings = convertToViewedRating(user.getRatings());
    
    model.addAttribute("ratings", ratings);
    return "all";
  }

  /**
   * Fetches the User from database. Creates one if necessary.
   * 
   * @param sub - unique google identifier
   * @return - The User object corresponding to user.
   */
  private User fetchUser(String sub) {
    User user;
    Optional<User> userOpt = userRepository.findBygoogleSub(sub);

    if (userOpt.isPresent()) {
      user = userOpt.get();
      
    } else {
      // create user
      // fix later to log and continue
      throw new RuntimeException("Fetching user that doesn't exist");
    }
    return user;
  }

  /**
   * Receives get request for new ratings
   */
  @GetMapping("/new/rating")
  public String ratingForm(Model model) {
    // insert NewRating that will be populated by form
    model.addAttribute("newRating", new ViewedRating());
    return "rating";
  }

    /**
   * Recieves post request and adds an entry in the MySQL database.
   * 
   * @param submittedRating
   * @param oauth
   * @return
   */
  @PostMapping("/new/rating")
  public String addRating(Model model, @ModelAttribute("newRating") ViewedRating submittedRating, OAuth2Authentication oauth) {
    Authentication auth = oauth.getUserAuthentication();
    ObjectMapper m = new ObjectMapper();

    @SuppressWarnings("unchecked")
    HashMap<String, Object> details  = m.convertValue(auth.getDetails(), HashMap.class);

    User user = fetchUser((String) details.get("sub"));
    Rating rating = new Rating(user, submittedRating.getDay(), submittedRating.getRating());
    
    ratingsRepository.save(rating);

    ArrayList<ViewedRating> ratings = convertToViewedRating(user.getRatings());
    
    model.addAttribute("ratings", ratings);
    // TODO: fix later: redirect to page where user can view all ratings
    return "all";
  }

  /**
   * Class that handles comparing two ratings
   */
  class RatingsCompare implements Comparator<ViewedRating> {
    /**
     * rating1 < rating2 if rating1.day < rating2.day
     */
    @Override
    public int compare(ViewedRating o1, ViewedRating o2) {
        return o1.getDay().compareTo(o2.getDay());
    }
  }

  /**
   * Converts db ratings into ratings user can view
   * 
   * @param ratings
   * @return
   */
  private ArrayList<ViewedRating> convertToViewedRating(Set<Rating> ratings) {
    ArrayList<ViewedRating> newRatings = new ArrayList<>();
    for (Rating rating: ratings) {
      ViewedRating newRating = new ViewedRating();
      newRating.setDay(rating.getId().getDay());
      newRating.setRating(rating.getRating());
      newRatings.add(newRating);
    }

    Collections.sort(newRatings, new RatingsCompare());
    return newRatings;

  } 
  
}