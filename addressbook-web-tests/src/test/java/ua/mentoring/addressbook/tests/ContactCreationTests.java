package ua.mentoring.addressbook.tests;

import org.testng.annotations.Test;
import ua.mentoring.addressbook.model.ContactData;

import java.io.File;

public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void ContactCreation() {
    applicationManager.goTo().gotoHomePage();
    applicationManager.getContactHelper().initContactCreation();
    File photo = new File("src/test/resources/stru.png");
    applicationManager.getContactHelper().fillContactForm
            (new ContactData("test_name","test_lastname", "test1"), true);
    applicationManager.getContactHelper().submitContactCreation();
    applicationManager.getContactHelper().returnToHomePage();
  }
}
