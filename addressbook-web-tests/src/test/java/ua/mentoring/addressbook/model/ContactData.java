package ua.mentoring.addressbook.model;

import java.io.File;

public class ContactData {

  private final String firstname;
  private final String secondname;
  private  String group;

  public File getPhoto() {
    return photo;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  private File photo;

  public ContactData (String firstname, String secondname, String group){
    this.firstname = firstname;
    this.secondname = secondname;
    this.group = group;
  }

  public String getFirstName() {return firstname;}

  public String getLastName() {return secondname;}

  public String getGroup() { return group; }





}
