package qa.instagram.UI;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import qa.instagram.core.WebDriverFactory;
import qa.instagram.utils.TestConfig;


public class BaseTest {
  protected static Logger logger = LogManager.getLogger(BaseTest.class);
  protected static final TestConfig testConfig = ConfigFactory.create(TestConfig.class);

  @BeforeClass
  public static void beforeMethod() {
    logger.info("UI BeforeClass has been started");
    WebDriverFactory.create(WebDriverFactory.BROWSER_CHROME);
    WebDriverFactory.getDriver().get(testConfig.siteUrl()); //get = open page
  }

  @AfterClass//(alwaysRun = true)
  public static void afterMethod() {
    logger.info("UI AfterClass has been started");
    WebDriverFactory.tearDown();
  }
}
