package qa.instagram;

import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import qa.instagram.core.WebDriverFactory;
import qa.instagram.utils.TestConfig;

public class BaseTest {
  protected TestConfig testConfig = ConfigFactory.create(TestConfig.class);

  @BeforeMethod
  public void beforeMethod() {
    WebDriverFactory.driverInit();
    WebDriverFactory.getDriver().get(testConfig.siteUrl()); //get = open page
  }

  @AfterMethod (alwaysRun = true)
  public void afterMethod() {
    WebDriverFactory.tearDown();
  }
}
