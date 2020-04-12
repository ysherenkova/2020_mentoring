package qa.instagram.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

  public static final String BROWSER_CHROME = "chrome";
  public static final String BROWSER_FIREFOX = "firefox";
  public static final String BROWSER_SAFARI = "safari";
  public static final String BROWSER_EDGE = "edge";

  public static WebDriver driver;

  public static WebDriver create(String browser) {
    switch (browser) {
      case (BROWSER_CHROME):
        driver = new ChromeDriver();
        break;
      case (BROWSER_FIREFOX):
        driver = new FirefoxDriver();
        break;
      case (BROWSER_SAFARI):
        driver = new SafariDriver();
        break;
      case (BROWSER_EDGE):
        driver = new EdgeDriver();
        break;
      default:
        throw new RuntimeException("Not implemented for " + browser);
    }

    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    return driver;
  }

  public static WebDriver getDriver() {
    return driver;
  }

  public static void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
