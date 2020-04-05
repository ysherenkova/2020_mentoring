package qa.instagram.utils;

import org.openqa.selenium.JavascriptExecutor;
import qa.instagram.core.WebDriverFactory;

public class Navigation {

  private static Integer STEP = 250;
  private static final Integer STEP_INCREMENT_PX = 550;

  public static void scrollDown() {
    JavascriptExecutor jse = (JavascriptExecutor) WebDriverFactory.getDriver();
    jse.executeScript(String.format("window.scrollBy(0,%d)", STEP += STEP_INCREMENT_PX), "");
  }
}
