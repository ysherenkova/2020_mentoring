package ua.mentoring.addressbook.tests;

import org.testng.annotations.Test;
import ua.mentoring.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupNotification () {
    applicationManager.getNavigationHelper().gotoGroupPage();
    applicationManager.getGroupHelper().selectGroup();
    applicationManager.getGroupHelper().initGroupModification();
    applicationManager.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    applicationManager.getGroupHelper().submitGroupModification();
    applicationManager.getGroupHelper().returnToGroupPage();
  }
}
