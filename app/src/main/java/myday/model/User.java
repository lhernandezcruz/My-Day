package myday.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Class that stores user db table
 */
@Entity
@Table(name = "users")
public class User {

  /**
   * Id of the user. autoincremented
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * Unique google identifier
   */
  @Column(name = "google_sub")
  private String googleSub;

  /**
   * Ratings this user owns
   */
  @OneToMany(mappedBy = "id.user", fetch=FetchType.EAGER)
  private Set<Rating> ratings;

  public User() {
      // nothing to do here
  }

  public User(Integer id, String sub) {
    this.id = id;
    this.googleSub = sub;
  }

  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }
  
  /**
   * @return the google_sub
   */
  public String getGoogleSub() {
    return googleSub;
  }

  /**
   * @return the ratings
   */
  public Set<Rating> getRatings() {
    return ratings;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @param googleSub the googleSub to set
   */
  public void setGoogleSub(String googleSub) {
    this.googleSub = googleSub;
  }

  /**
   * @param ratings the ratings to set
   */
  public void setRatings(Set<Rating> ratings) {
    this.ratings = ratings;
  }


  @Override
  public boolean equals(Object obj) {

    if (obj == null || !(obj instanceof User)) {
      return false;
    }

    User other = (User) obj;
    return other.id.equals(this.id); // sufficient for the ids to match up
  }

  @Override
  public String toString() {
    return "User{ id: " + this.id + 
                  ", google_sub: " + this.googleSub +
                  " }";
  }
}
