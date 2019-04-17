package myday.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that stores ratings db information
 */
@Entity
@Table(name = "ratings")
public class Rating {

  /**
   * Ratings primary key
   */
  @EmbeddedId
  public RatingKey id;
  
  /**
   * Rating of the day
   */
  @Column(name="rating")
  private Integer rating;


  public Rating() {
    // nothing to do here 
  }

  public Rating(User user, Date day, Integer rating) {
    this.id = new RatingKey(user, day);
    this.rating = rating;
  }

  /**
   * @return the rating
   */
  public Integer getRating() {
    return rating;
  }
  
  /**
   * @param id the id to set
   */
  public void setId(RatingKey id) {
    this.id = id;
  }

  /**
   * Set the id using the user making rating and the day being rated.
   * 
   * @param user user who owns this rating
   * @param day  day being rated
   */
  public void setId(User user, Date day) {
    this.id = new RatingKey(user, day);
  }

  /**
   * @param rating the rating to set
   */
  public void setRating(Integer rating) {
    this.rating = rating;
  }

  /**
   * @return the id
   */
  public RatingKey getId() {
    return id;
  }

  
  @Override
  public boolean equals(Object obj) {

    if (obj == null || !(obj instanceof Rating)) {
      return false;
    }

    Rating other = (Rating) obj;
    return other.id.equals(this.id); // sufficient for the ids to match up
  }

  @Override
  public String toString() {
    return "Rating{ id: " + this.id + 
                  ", rating: " + this.rating +
                  " }";
  }
}
