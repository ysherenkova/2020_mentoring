package ua.mentoring.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ua.mentoring.addressbook.appManager.ApplicationManager;
import ua.mentoring.addressbook.model.BrowserType;

import java.io.IOException;

public class TestBase {

  protected static final ApplicationManager applicationManager
          = new ApplicationManager(System.getProperty("browser", String.valueOf(BrowserType.CHROME))); //here we can change the browser

 // @BeforeMethod
  @BeforeSuite
  public void setUp() throws IOException {
    applicationManager.init();
  }

 // @AfterMethod
  @AfterSuite
  public void tearDown() {
    applicationManager.stop();
  }

}
