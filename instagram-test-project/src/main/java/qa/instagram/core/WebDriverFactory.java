package qa.instagram.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

  private static WebDriver driver;

  public static void driverInit() {
    WebDriverManager.chromedriver().arch64().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
