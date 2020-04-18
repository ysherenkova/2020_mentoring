package qa.instagram.DB;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import qa.instagram.core.DbListener;
import qa.instagram.core.WebDriverFactory;
import qa.instagram.utils.TestConfig;

@Listeners({DbListener.class})
public class BaseTest {

  protected static final TestConfig testConfig = ConfigFactory.create(TestConfig.class);
  protected static Logger logger = LogManager.getLogger(qa.instagram.DB.BaseTest.class);

  @BeforeMethod
  public static void beforeMethod() {
    logger.info("DB BeforeClass has been started");
    WebDriverFactory.create(WebDriverFactory.BROWSER_CHROME);
    WebDriverFactory.getDriver().get(testConfig.siteUrl()); //get = open page
  }

  @AfterMethod
  public static void afterMethod() {
    logger.info("DB AfterClass has been started");
    WebDriverFactory.tearDown();
  }
}
