package ua.mentoring.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.mentoring.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    applicationManager.getNavigationHelper().gotoGroupPage();
    if (! applicationManager.getGroupHelper().IsThereAnyGroup()) {
      applicationManager.getGroupHelper().createGroup(new GroupData("NewGroup", "", ""));
    }
    List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
    applicationManager.getGroupHelper().selectGroup(before.size() - 1);
    applicationManager.getGroupHelper().initGroupModification();
    applicationManager.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    applicationManager.getGroupHelper().submitGroupModification();
    applicationManager.getGroupHelper().returnToGroupPage();
    List<GroupData> after = applicationManager.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());
  }
}
