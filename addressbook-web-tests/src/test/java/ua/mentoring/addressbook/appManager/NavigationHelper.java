package ua.mentoring.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{

  public NavigationHelper(FirefoxDriver driver) {
    super(driver);
  }

  public void gotoGroupPage() {
    click(By.linkText("group page"));
  }
}
