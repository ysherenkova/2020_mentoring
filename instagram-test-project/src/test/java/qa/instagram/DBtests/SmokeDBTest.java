package qa.instagram.DBtests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import qa.instagram.pages.MyAccountPage;
import qa.instagram.pages.MyFeedPage;
import qa.instagram.pages.SignInPage;

import java.util.Map;

import static qa.instagram.pages.SignInPage.createSignInPage;

public class SmokeDBTest extends BaseTest {

  @Test
  public void connectionTest() {
    SignInPage signInPage = createSignInPage();
    signInPage.setLogin(testConfig.targetAccount());
    signInPage.setPassword(testConfig.targetPassword());
    MyFeedPage myFeedPage = signInPage.clickSignInButton();
    myFeedPage.turnOffNotifications();
    MyAccountPage myAccountPage = myFeedPage.clickAccountName();

    Map<WebElement, String> allPhotos = myAccountPage.getAllPhotos();
    Assert.assertEquals(allPhotos.size(), myAccountPage.getNumberOfPost());


  }
}
