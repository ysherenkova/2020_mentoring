package qa.instagram.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qa.instagram.core.WebDriverFactory;
import qa.instagram.utils.WebElementUtils;

@SuppressWarnings("unused")
public class MyFeedPage {
  @FindBy(xpath = "//*[@id=\"react-root\"]/section/main/section/div[3]/div[1]/div/div[2]/div[1]/a")
  WebElement accountName;

  @FindBy(xpath = "/html/body/div[4]/div/div/div[3]/button[2]")
  WebElement btnPopupNotNow;

  public MyAccountPage clickAccountName() {
    WebElementUtils.waitTillAvailable(accountName).click();
    return PageFactory.initElements(WebDriverFactory.getDriver(), MyAccountPage.class);
  }

  public void turnOffNotifications() {
    WebElementUtils.waitTillAvailable(btnPopupNotNow);
    btnPopupNotNow.click();
  }
}