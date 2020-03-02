package ua.mentoring.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.mentoring.addressbook.model.GroupData;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
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
    GroupData group = new GroupData(before.get(before.size() - 1).getId(), "test1", "test2", "test3");
    applicationManager.getGroupHelper().fillGroupForm(group);
    applicationManager.getGroupHelper().submitGroupModification();
    applicationManager.getGroupHelper().returnToGroupPage();
    List<GroupData> after = applicationManager.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);
    Comparator<? super GroupData> byId = (g1,g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);

    //преобразовываем списки в множества
   // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
