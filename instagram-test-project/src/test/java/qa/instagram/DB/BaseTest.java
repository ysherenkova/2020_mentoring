package qa.instagram.DB;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.testcontainers.containers.MariaDBContainer;
import qa.instagram.core.WebDriverFactory;
import qa.instagram.utils.DBManager;
import qa.instagram.utils.TestConfig;

public class BaseTest {
  @ClassRule
  public static final MariaDBContainer mariaDB = new MariaDBContainer()
          .withDatabaseName(DBManager.getInstance().getDataBaseName());
  protected static final TestConfig testConfig = ConfigFactory.create(TestConfig.class);
  protected static Logger logger = LogManager.getLogger(qa.instagram.DB.BaseTest.class);

  @BeforeClass
  public static void beforeMethod() {
    logger.info("DB BeforeClass has been started");
    WebDriverFactory.create(WebDriverFactory.BROWSER_CHROME);
    WebDriverFactory.getDriver().get(testConfig.siteUrl()); //get = open page

    logger.info("Init DB");
    DBManager.initJdbcTemplate(mariaDB);
    DBManager.getInstance().createTable(testConfig.photosTableName());
  }

  @AfterClass
  public static void afterMethod() {
    logger.info("DB AfterClass has been started");
    WebDriverFactory.tearDown();
  }
}
