package ua.mentoring.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ua.mentoring.addressbook.appManager.ApplicationManager;
import ua.mentoring.addressbook.model.BrowserType;

public class TestBase {

  protected final ApplicationManager applicationManager = new ApplicationManager(BrowserType.FIREFOX); //here we can change the browser

  @BeforeMethod
  public void setUp() {
    applicationManager.init();
  }

  @AfterMethod
  public void tearDown() {
    applicationManager.stop();
  }

}
