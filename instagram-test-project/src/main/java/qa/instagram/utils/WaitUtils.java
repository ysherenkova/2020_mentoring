package qa.instagram.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import qa.instagram.core.WebDriverFactory;

import java.time.Duration;

public class WaitUtils {

  public static WebElement waitTillAvailable(WebElement element) {
    return new WebDriverWait(WebDriverFactory.getDriver(),5)
            .pollingEvery(Duration.ofSeconds(1))
            .until(ExpectedConditions.visibilityOf(element));
  }
}
