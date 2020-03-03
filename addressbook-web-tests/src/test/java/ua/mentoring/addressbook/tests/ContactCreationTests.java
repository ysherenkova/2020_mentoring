package ua.mentoring.addressbook.tests;

import org.testng.annotations.Test;
import ua.mentoring.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void ContactCreation() {
    applicationManager.goTo().gotoHomePage();
    applicationManager.getContactHelper().initContactCreation();
    applicationManager.getContactHelper().fillContactForm(new ContactData("test_name","test_lastname", "test1"), true);
    applicationManager.getContactHelper().submitContactCreation();
    applicationManager.getContactHelper().returnToHomePage();

  }
}
