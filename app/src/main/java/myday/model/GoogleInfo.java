package myday.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that stores information about google_info db table
 */
@Entity
@Table(name = "googleinfo")
public class GoogleInfo {
  
  /**
   * Primary key for the table
   */
  @EmbeddedId
  public GoogleInfoKey id;

  /**
   * Url to google profile pic
   */
  @Column(name = "pic_url")
  private String picUrl;

  /**
   * Name of the user
   */
  @Column(name = "name")
  private String name;


  public GoogleInfo() {
    // nothing to do here 
  }

  public GoogleInfo(User user, String picUrl, String name) {
    this.id = new GoogleInfoKey(user);
    this.picUrl = picUrl;
    this.name = name;
  }

  /**
   * @return the id
   */
  public GoogleInfoKey getId() {
    return id;
  }

  /**
   * set the user who owns this info
   */
  public void setId(User user) {
    this.id = new GoogleInfoKey(user);
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the picUrl
   */
  public String getPicUrl() {
    return picUrl;
  }
  

  /**
   * @param id the id to set
   */
  public void setId(GoogleInfoKey id) {
    this.id = id;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @param picUrl the picUrl to set
   */
  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == null || !(obj instanceof GoogleInfo)) {
      return false;
    }

    GoogleInfo other = (GoogleInfo) obj;
    return other.id.equals(this.id); // sufficient for the ids to match up
  }

  @Override
  public String toString() {
    return "GoogleInfo{ id: " + this.id + 
                  ", name: " + this.name +
                  ", picUrl: " + this.picUrl + 
                  " }";
  }

}
