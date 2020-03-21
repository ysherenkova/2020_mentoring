package qa.instagram;

import org.aeonbits.owner.ConfigFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import qa.instagram.core.WebDriverFactory;
import qa.instagram.utils.TestConfig;


public class BaseTest {
  protected static TestConfig testConfig = ConfigFactory.create(TestConfig.class);

  @BeforeClass
  public static void beforeMethod() {
    WebDriverFactory.driverInit();
    WebDriverFactory.getDriver().get(testConfig.siteUrl()); //get = open page
  }

  @AfterClass//(alwaysRun = true)
  public static void afterMethod() {
    WebDriverFactory.tearDown();
  }
}
