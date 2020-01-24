package ua.mentoring.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver driver) {
    super(driver);
  }

  public void gotoGroupPage() {
    click(By.xpath("/html/body/div/div[3]/ul/li[3]/a"));
  }

  public void gotoHomePage() {
  }
}
