package myday.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Class that stores primary key of GoogleInfo table
 */
@Embeddable
public class GoogleInfoKey implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * User to join with
   */
  @OneToOne
  @JoinColumn(name = "google_sub", referencedColumnName = "google_sub")
  private User user;

  public GoogleInfoKey() {
  }

  public GoogleInfoKey(User user) {
    this.user = user;
  }


  /**
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * @param user the user to set
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Equal if the users are equal.
   */
  @Override
  public boolean equals(Object obj) {

    if (obj == null || !(obj instanceof GoogleInfoKey)) {
      return false;
    }

    GoogleInfoKey other = (GoogleInfoKey) obj;
    return other.user.equals(this.user);
  }

  @Override
  public String toString() {
    return "GoogleInfoKey{ user: " + this.user + "}";
  }
}