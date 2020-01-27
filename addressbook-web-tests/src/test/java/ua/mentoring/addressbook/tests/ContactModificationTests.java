package ua.mentoring.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.mentoring.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    applicationManager.getNavigationHelper().gotoHomePage();
    applicationManager.getContactHelper().initContactModification();
    applicationManager.getContactHelper().fillContactForm(new ContactData("test_name", "test_surname", ""), false);
    applicationManager.getContactHelper().submitContactModification();
    applicationManager.getContactHelper().returnToHomePage();
  }
}
