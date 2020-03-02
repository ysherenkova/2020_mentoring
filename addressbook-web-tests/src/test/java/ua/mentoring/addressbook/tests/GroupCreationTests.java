package ua.mentoring.addressbook.tests;// Generated by Selenium IDE

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.mentoring.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    applicationManager.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
    GroupData group = new GroupData("NewGroup123", "", "");
    applicationManager.getGroupHelper().createGroup(group);
    List<GroupData> after = applicationManager.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() +1);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);

  }

}
