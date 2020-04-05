package qa.instagram.UI;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import qa.instagram.pages.MyAccountPage;
import qa.instagram.pages.MyFeedPage;
import qa.instagram.pages.SignInPage;

import java.util.Map;

import static qa.instagram.pages.SignInPage.createSignInPage;

public class SmokeTest extends BaseTest {

  @Test
  public void loginTest() {
    SignInPage signInPage = createSignInPage();
    signInPage.setLogin(testConfig.targetAccount());
    signInPage.setPassword(testConfig.targetPassword());
    MyFeedPage myFeedPage = signInPage.clickSignInButton();
    myFeedPage.turnOffNotifications();
    MyAccountPage myAccountPage = myFeedPage.clickAccountName();

    Map<WebElement, String> allPhotos = myAccountPage.getAllPhotos();
    Assert.assertEquals(allPhotos.size(), myAccountPage.getNumberOfPost());

    // TODO uncomment, tranfer storeToFs() and storeToDB() from DBtest to common entity
//    DownloadUtils.downloadAllPhotos(allPhotos, (new File(testConfig.galleryAddress())).getAbsolutePath(), testConfig.photoDownloaderThreadLimit());
//    Assert.assertEquals(
//            allPhotos.size(),
//            getNumberOfFilesInGallery(new File(testConfig.galleryAddress()).getAbsolutePath())
//    );
  }
}
