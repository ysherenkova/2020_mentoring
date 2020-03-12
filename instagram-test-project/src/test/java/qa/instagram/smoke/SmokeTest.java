package qa.instagram.smoke;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import qa.instagram.BaseTest;
import qa.instagram.pages.MyAccountPage;
import qa.instagram.pages.MyFeedPage;
import qa.instagram.pages.SignInPage;
import qa.instagram.utils.FileUtils;
import qa.instagram.utils.Navigation;

import java.util.Map;

import static qa.instagram.pages.SignInPage.createSignInPage;
import static qa.instagram.utils.FileUtils.getNumberOfFilesInGallery;

public class SmokeTest extends BaseTest {

  @Test
  public void loginTest() {
    SignInPage signInPage = createSignInPage();
    signInPage.setLogin(testConfig.targetAccount());
    signInPage.setPassword(testConfig.targetPassword());
    MyFeedPage myFeedPage = signInPage.clickSignInButton();
    myFeedPage.turnOffNotifications();
    MyAccountPage myAccountPage = myFeedPage.clickAccountName();
//    myAccountPage.getAllPhotos();
    //actual result first
    Map<WebElement, String> allPhotos = myAccountPage.getAllPhotos();
    Assert.assertEquals(allPhotos.size(), myAccountPage.getNumberOfPost());

    FileUtils.downloadAllPhotos(allPhotos, testConfig.galleryAddress());
    Assert.assertEquals(allPhotos.size(),getNumberOfFilesInGallery(testConfig.galleryAddress()));
  }
}
