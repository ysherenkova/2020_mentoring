package ua.mentoring.addressbook.appManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ua.mentoring.addressbook.model.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private BrowserType browser;
  private WebDriver driver;

  private SessionHelper sessionHelper;
  private  NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;

  public ApplicationManager (BrowserType browser) {
    this.browser = browser;
  }

  public void init() {
    if (browser.equals(BrowserType.CHROME)) {
      WebDriverManager.chromedriver().arch64().setup();
      driver = new ChromeDriver();
    }
    else if (browser.equals(BrowserType.FIREFOX)) {
      WebDriverManager.firefoxdriver().arch64().setup();
      driver = new FirefoxDriver();
    }

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get("http://localhost/addressbook/index.php");
    groupHelper = new GroupHelper (driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    sessionHelper.login("admin","secret");
  }

  public void stop() {
    driver.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
