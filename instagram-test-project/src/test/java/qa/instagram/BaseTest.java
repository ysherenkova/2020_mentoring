package qa.instagram;

import org.aeonbits.owner.ConfigFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import qa.instagram.core.WebDriverFactory;
import qa.instagram.utils.TestConfig;


public class BaseTest {
  protected TestConfig testConfig = ConfigFactory.create(TestConfig.class);

  @BeforeClass
  public void beforeMethod() {
    WebDriverFactory.driverInit();
    WebDriverFactory.getDriver().get(testConfig.siteUrl()); //get = open page
  }

  @AfterClass//(alwaysRun = true)
  public void afterMethod() {
    WebDriverFactory.tearDown();
  }
}
