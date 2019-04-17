package myday.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Class that stores primary key of rating db table
 */
@Embeddable
public class RatingKey implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * User who owns this rating
   */
  @ManyToOne
  @JoinColumn(name = "id")
  private User user;

  /**
   * Day being rated
   */
  @Column(name = "day")
  private Date day;


  public RatingKey() {
  }

  public RatingKey(User user, Date day) {
    this.user = user;
    this.day = day;
  }

  /**
   * @return the day
   */
  public Date getDay() {
    return day;
  }

  /**
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * @param day the day to set
   */
  public void setDay(Date day) {
    this.day = day;
  }

  /**
   * @param user the user to set
   */
  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == null || !(obj instanceof RatingKey)) {
      return false;
    }

    RatingKey other = (RatingKey) obj;
    return other.user.equals(this.user) && other.day.equals(this.day);
  }

  @Override
  public String toString() {
    return "RatingKey{ user: " + this.user + 
                  ", day: " + this.day +
                  " }";
  }
}