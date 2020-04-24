package qa.instagram.UI;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import qa.instagram.core.WebDriverFactory;
import qa.instagram.di.WebDriverModule;
import qa.instagram.utils.TestConfig;

import java.io.File;

public class BaseTest {
  protected static final TestConfig testConfig = ConfigFactory.create(TestConfig.class);
  protected static Logger logger = LogManager.getLogger(BaseTest.class);


  @BeforeMethod
  public void beforeMethod() {
    logger.info("UI BeforeClass has been started");

    Injector injector = Guice.createInjector(new WebDriverModule());

    String browser = System.getProperty("browser");
    System.out.println(browser);

    WebDriverFactory.create(browser);
    WebDriverFactory.getDriver().get(testConfig.siteUrl()); //


    //get = open page
    (new File(testConfig.galleryAddress())).delete();
  }


  @AfterMethod
  public void afterMethod() {
    logger.info("UI AfterClass has been started");
    WebDriverFactory.tearDown();
  }
}
