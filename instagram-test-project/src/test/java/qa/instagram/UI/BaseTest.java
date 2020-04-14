package qa.instagram.UI;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import qa.instagram.core.WebDriverFactory;
import qa.instagram.utils.TestConfig;


public class BaseTest {
  protected static final TestConfig testConfig = ConfigFactory.create(TestConfig.class);
  protected static Logger logger = LogManager.getLogger(BaseTest.class);

  @BeforeMethod
  public void beforeMethod() {
    logger.info("UI BeforeClass has been started");
    WebDriverFactory.create(WebDriverFactory.BROWSER_CHROME);
    WebDriverFactory.getDriver().get(testConfig.siteUrl()); //get = open page
  }


  @AfterMethod
  public void afterMethod() {
    logger.info("UI AfterClass has been started");
    WebDriverFactory.tearDown();
  }
}
