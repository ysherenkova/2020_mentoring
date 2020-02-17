package qa.instagram.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qa.instagram.core.WebDriverFactory;

import java.util.Arrays;
import java.util.Base64;

public class SignInPage {
  @FindBy(xpath = "//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[2]/div/label/input")
  WebElement login;

  @FindBy(xpath = "//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[3]/div/label/input")
  WebElement password;

  @FindBy(xpath = "//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[4]/button/div")
  WebElement signInButton;

  public static SignInPage createSignInPage() {
    return PageFactory.initElements(WebDriverFactory.getDriver(),SignInPage.class);
  }



  public SignInPage setLogin(String login) {
    this.login.sendKeys(login);
    return this;
  }

  public SignInPage setPassword (String password) {
    byte[] decode = Base64.getDecoder().decode(password);
    this.password.sendKeys(new String(decode));
    return this;
  }

  public MyFeedPage clickSignInButton() {
    this.signInButton.click();
    return PageFactory.initElements(WebDriverFactory.getDriver(), MyFeedPage.class);
  }
}
