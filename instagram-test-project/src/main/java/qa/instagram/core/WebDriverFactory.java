package qa.instagram.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
  protected static Logger logger = LogManager.getLogger(WebDriverFactory.class);

  public static final String BROWSER_CHROME = "chrome";
  public static final String BROWSER_FIREFOX = "firefox";
  public static final String BROWSER_SAFARI = "safari";
  public static final String BROWSER_EDGE = "edge";
  public static final String SELENOID = "selenoid";

  public static WebDriver driver;

  public static WebDriver create(String browser) {
    switch (browser) {
      case (BROWSER_CHROME):
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        driver = new ChromeDriver(chromeOptions);
        //     WebDriverManager.chromedriver().arch64().setup();
        System.setProperty("webdriver.chrome.path", "/Users/yuliia_sherenkova/Documents/GitHub/2020_mentoring/instagram-test-project/chromedriver");
        logger.info("Chrome browser was chosen");
        break;
      case (BROWSER_FIREFOX):
        driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().arch64().setup();
        logger.info("FireFox browser was chosen");
        break;
      case (BROWSER_SAFARI):
        driver = new SafariDriver();
        logger.info("Safari browser was chosen");
        break;
      case (BROWSER_EDGE):
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setPageLoadStrategy("normal");
        driver = new EdgeDriver(edgeOptions);
        logger.info("Edge browser was chosen");
        break;
      case (SELENOID):
        SelenoidWebDriverProvider provider = new SelenoidWebDriverProvider();
        driver = provider.createDriver(null);
        logger.info("Selenoid browser was chosen");
        break;
      default:
        logger.error("No browser was set up");
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
