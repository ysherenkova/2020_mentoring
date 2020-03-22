package qa.instagram.DB;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import qa.instagram.pages.MyAccountPage;
import qa.instagram.pages.MyFeedPage;
import qa.instagram.pages.SignInPage;
import qa.instagram.utils.DBManager;
import qa.instagram.utils.DownloaderUtils;

import java.io.File;
import java.util.Map;

import static qa.instagram.pages.SignInPage.createSignInPage;

public class SmokeTest extends BaseTest {

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

    DownloaderUtils.downloadAllPhotos(allPhotos, (new File(testConfig.galleryAddress())).getAbsolutePath(), testConfig.numberOfThreads());

    //сравнить количество
    Assert.assertEquals(
            myAccountPage.getNumberOfPost(),
            DBManager.getInstance().getCountOfDBRecords()
    );


  }
}
