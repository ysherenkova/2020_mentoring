package ua.mentoring.addressbook.tests;

import  org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.mentoring.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    applicationManager.goTo().groupPage();
    if (applicationManager.group().all().size() == 0 ) {
      applicationManager.group().create(new GroupData().withName("NewGroup"));
    }
  }

  @Test
  public void testGroupModification() {
    Set<GroupData> before = applicationManager.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("test1")
            .withHeader("test2")
            .withFooter("test3");
    applicationManager.group().modify(group);
    Set<GroupData> after = applicationManager.group().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after);

    //преобразовываем списки в множества
   // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
