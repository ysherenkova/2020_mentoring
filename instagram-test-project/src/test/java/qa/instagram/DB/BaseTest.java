package qa.instagram.DB;

import org.aeonbits.owner.ConfigFactory;
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

  @BeforeClass
  public static void beforeMethod() {
    //driver
    WebDriverFactory.driverInit();
    WebDriverFactory.getDriver().get(testConfig.siteUrl()); //get = open page

    System.out.println("Init DB");
    //init db
    DBManager.initJdbcTemplate(mariaDB);
    DBManager.getInstance().createTable(testConfig.photosTableName());
  }

  @AfterClass
  public static void afterMethod() {
    WebDriverFactory.tearDown();
  }
}