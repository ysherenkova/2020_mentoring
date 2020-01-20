package ua.mentoring.addressbook.tests;

import org.junit.After;
import org.junit.Before;
import ua.mentoring.addressbook.appManager.ApplicationManager;

public class TestBase {

  protected final ApplicationManager applicationManager = new ApplicationManager();

  @Before
  public void setUp() {
    applicationManager.init();
  }

  @After
  public void tearDown() {
    applicationManager.stop();
  }

}
