package qa.instagram.core;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class SelenoidWebDriverProvider {
  public WebDriver createDriver(DesiredCapabilities capabilities) {
    DesiredCapabilities browser = new DesiredCapabilities();
    browser.setBrowserName("chrome");
    browser.setVersion("81.0");
    browser.setCapability("enableVNC", true);

    try {
      RemoteWebDriver driver = new RemoteWebDriver(
              URI.create("http://localhost:4444/wd/hub").toURL(),
              browser
      );
      driver.manage().window().setSize(new Dimension(1280, 1024));
      return driver;
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}