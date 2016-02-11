package fruitbasket.litchee.solr.entity;

import java.util.Date;
import java.util.List;


public class DocApi {

  private String id;
  private String title;
  private String description;
  private List<String> categoryList;
  private Boolean enabled;
  private Date solrRegisteredAt;


  /* Constructor */
  public DocApi(){}


  /* Getter, setter */
  public String getId() {
    return this.id;
  }
  public void setId(String val) {
    this.id = val;
  }

  public String getTitle() {
    return this.title;
  }
  public void setTitle(String val) {
    this.title = val;
  }

  public String getDescription() {
    return this.description;
  }
  public void setDescription(String val) {
    this.description = val;
  }

  public List<String> getCategoryList() {
    return this.categoryList;
  }
  public void setCategoryList(List<String> val) {
    this.categoryList = val;
  }

  public Boolean getEnabled() {
    return this.enabled;
  }
  public void setEnabled(Boolean val) {
    this.enabled = val;
  }

  public Date getSolrRegisteredAt() {
    return solrRegisteredAt;
  }
  public void setSolrRegisteredAt(Date val) {
    this.solrRegisteredAt = val;
  }

}
