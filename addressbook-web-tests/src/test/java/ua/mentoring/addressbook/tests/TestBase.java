package ua.mentoring.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ua.mentoring.addressbook.appManager.ApplicationManager;
import ua.mentoring.addressbook.model.BrowserType;

public class TestBase {

  protected static final ApplicationManager applicationManager = new ApplicationManager(BrowserType.FIREFOX); //here we can change the browser

 // @BeforeMethod
  @BeforeSuite
  public void setUp() {
    applicationManager.init();
  }

 // @AfterMethod
  @AfterSuite
  public void tearDown() {
    applicationManager.stop();
  }

}
