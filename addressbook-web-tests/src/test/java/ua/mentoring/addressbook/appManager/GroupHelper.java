package ua.mentoring.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.mentoring.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver driver) {
    super(driver);
  }

  public void returnToGroupPage() {
    driver.findElement(By.linkText("group page")).click();
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"),groupData.getName());
    type(By.name("group_header"),groupData.getHeader());
    type(By.name("group_footer"),groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroup(int index){
    driver.findElements(By.cssSelector("#content > form > span > input[type=checkbox]")).get(index).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public boolean IsThereAnyGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
   return driver.findElements(By.cssSelector("#content > form > span > input[type=checkbox]")).size();
  }

  public List<GroupData> getGroupList() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      GroupData group = new GroupData(name, "", "");
      groups.add(group);
    }
    return groups;
  }
}
