package ua.mentoring.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ua.mentoring.addressbook.model.ContactData;

public class ContactHelper extends  HelperBase {
  public ContactHelper(WebDriver driver) { super(driver); }


  public void initContactCreation() {
    click(By.linkText("add new")); }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.xpath("//*[@id=\"content\"]/form/input[5]"), contactData.getLastName());
    if (creation) {
      new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }
   else {
      Assert.assertFalse(isElementPresent(By.xpath("//*[@id=\"content\"]/form/select[5]")));
    }
  }

  private boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;}
    }


  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void returnToHomePage() {
    click(By.xpath("//*[@id=\"nav\"]/ul/li[1]/a"));
  }

  public void initContactModification() {
    click(By.cssSelector("img[alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

}