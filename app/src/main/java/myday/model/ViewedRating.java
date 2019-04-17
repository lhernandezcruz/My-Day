package myday.model;

import java.sql.Date;


/**
 * Class that stores information of newly submitted rating
 */
public class ViewedRating {
  /**
   * Rating of the day
   */
  private Integer rating;

  /**
   * Date of day being rated
   */
  private Date day;

  public ViewedRating() {
    // nothing to do here
  }

  /**
   * @return the day
   */
  public Date getDay() {
    return day;
  }

  /**
   * @return the rating
   */
  public Integer getRating() {
    return rating;
  }

  /**
   * @param day the day to set
   */
  public void setDay(Date day) {
    this.day = day;
  }

  /**
   * @param rating the rating to set
   */
  public void setRating(Integer rating) {
    this.rating = rating;
  }

  /**
   * Two NewRatings are equal if the day and the rating are equivalent
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof ViewedRating)) {
      return false;
    }

    ViewedRating other = (ViewedRating) obj;
    return other.rating == this.rating && other.day.equals(this.day);
  }

  @Override
  public String toString() {
    return "ViewedRating{ day: " + this.day + 
                  ", rating: " + this.rating +
                  "}";
  }
}