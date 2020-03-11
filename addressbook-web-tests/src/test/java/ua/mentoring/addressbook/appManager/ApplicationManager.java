package ua.mentoring.addressbook.appManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ua.mentoring.addressbook.model.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final Properties properties;
  private String browser;
  private WebDriver driver;

  private SessionHelper sessionHelper;
  private  NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;

  public ApplicationManager (String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    if (browser.equals(BrowserType.CHROME)) {
      WebDriverManager.chromedriver().arch64().setup();
      driver = new ChromeDriver();
    }
    else if (browser.equals(BrowserType.FIREFOX)) {
      WebDriverManager.firefoxdriver().arch64().setup();
      driver = new FirefoxDriver();
    }

    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.get(properties.getProperty("web.baseURL"));
    groupHelper = new GroupHelper (driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    sessionHelper.login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));
    contactHelper = new ContactHelper(driver);
  }

  public void stop() {
    driver.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
