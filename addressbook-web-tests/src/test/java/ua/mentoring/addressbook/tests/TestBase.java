package ua.mentoring.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ua.mentoring.addressbook.appManager.ApplicationManager;
import ua.mentoring.addressbook.model.BrowserType;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);
  protected static final ApplicationManager applicationManager
          = new ApplicationManager(System.getProperty("browser", String.valueOf(BrowserType.CHROME))); //here we can change the browser

 // @BeforeMethod
  @BeforeSuite
  public void setUp() throws IOException {
    applicationManager.init();
  }

 // @AfterMethod
  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    applicationManager.stop();
  }

  @BeforeMethod
  public void logTestStart(Method method, Object[] parameters) {
    logger.info("Start test " + method.getName() + " with parameters " + Arrays.asList(parameters));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method method, Object[] parameters) {
    logger.info("Stop test " + method.getName() + " with parameters " + Arrays.asList(parameters));
  }
  }
