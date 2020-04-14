package qa.instagram.DB;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import qa.instagram.db.DbPhotoManager;
import qa.instagram.pages.MyAccountPage;
import qa.instagram.pages.MyFeedPage;
import qa.instagram.pages.SignInPage;
import qa.instagram.utils.FileDownloader;
import qa.instagram.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static qa.instagram.pages.SignInPage.createSignInPage;

@Listeners(ReportPortalTestNGListener.class)
public class SmokeTest extends BaseTest {
  @Test(groups = {"DB", "Smoke"})
  public void connectionTest() {

    SignInPage signInPage = createSignInPage();
    signInPage.setLogin(testConfig.targetAccount());
    signInPage.setPassword(testConfig.targetPassword());
    MyFeedPage myFeedPage = signInPage.clickSignInButton();
    myFeedPage.turnOffNotifications();
    MyAccountPage myAccountPage = myFeedPage.clickAccountName();

    Map<WebElement, String> allPhotos = myAccountPage.getAllPhotos();
    Assert.assertEquals(allPhotos.size(), myAccountPage.getNumberOfPost());

    List<String> fileUrls = new ArrayList<>(allPhotos.values());
    FileDownloader downloader = new FileDownloader(testConfig.photoDownloaderThreadLimit());
    DbPhotoManager dbPhotoManager = new DbPhotoManager(testConfig.photosTableName());

    FileUtils.storeToFs(downloader, fileUrls, (new File(testConfig.galleryAddress()).getAbsolutePath()));
    dbPhotoManager.storeToDb(downloader, fileUrls);

    // Compare counter on the instagram page with count of the records in the DB
    Assert.assertEquals(
            myAccountPage.getNumberOfPost(),
            dbPhotoManager.getNumberOfPhotos());
  }


}
