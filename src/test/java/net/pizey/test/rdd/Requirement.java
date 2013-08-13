package net.pizey.test.rdd;

public class Requirement {

  private String title;
  private String id;

  public Requirement(String id, String title) {
    this.setId(id);
    this.setTitle(title);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}
