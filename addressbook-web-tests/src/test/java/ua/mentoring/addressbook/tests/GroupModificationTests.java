package ua.mentoring.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.mentoring.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    applicationManager.getNavigationHelper().gotoGroupPage();
    if (!applicationManager.getGroupHelper().IsThereAnyGroup()) {
      applicationManager.getGroupHelper().createGroup(new GroupData("NewGroup", "", ""));
    }
  }

  @Test
  public void testGroupModification() {
    List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
    int index = before.size() - 1;
    GroupData group = new GroupData(before.get(index).getId(), "test1", "test2", "test3");
    applicationManager.getGroupHelper().modifyGroup(index, group);
    List<GroupData> after = applicationManager.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (g1,g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);

    //преобразовываем списки в множества
   // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
