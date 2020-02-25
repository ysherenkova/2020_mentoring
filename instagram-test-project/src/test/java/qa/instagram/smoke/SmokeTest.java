package qa.instagram.smoke;

import org.testng.annotations.Test;
import qa.instagram.BaseTest;
import qa.instagram.pages.MyFeedPage;
import qa.instagram.pages.SignInPage;
import qa.instagram.utils.Navigation;

import static qa.instagram.pages.SignInPage.createSignInPage;

public class SmokeTest extends BaseTest {

  @Test
  public void loginTest() {
    SignInPage signInPage = createSignInPage();
    signInPage.setLogin(testConfig.targetAccount());
    signInPage.setPassword(testConfig.targetPassword());
    MyFeedPage myFeedPage = signInPage.clickSignInButton();
    myFeedPage.clickAccountName();
    Navigation.scrollDown();
  }
}
