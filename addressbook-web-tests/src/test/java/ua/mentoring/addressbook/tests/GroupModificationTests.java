package ua.mentoring.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import  org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.mentoring.addressbook.model.GroupData;
import ua.mentoring.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Groups before = applicationManager.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("test1")
            .withHeader("test2")
            .withFooter("test3");
    applicationManager.group().modify(group);
    assertThat(applicationManager.group().count(), equalTo(before.size()));
    Groups after = applicationManager.group().all();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    //преобразовываем списки в множества
   // Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
