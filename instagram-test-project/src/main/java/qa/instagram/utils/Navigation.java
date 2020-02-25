package qa.instagram.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import qa.instagram.core.WebDriverFactory;

import java.time.Duration;

public class Navigation {

  public static void scrollDown() {
    //chord allow me to concatanate keys (ctrl + A)
    //Keys.chord()
Actions actions = new Actions(WebDriverFactory.getDriver());
actions.keyDown(Keys.PAGE_DOWN)
        .pause(Duration.ofSeconds(10))
        .keyUp(Keys.PAGE_DOWN)
        .build()
        .perform();
  }
}
