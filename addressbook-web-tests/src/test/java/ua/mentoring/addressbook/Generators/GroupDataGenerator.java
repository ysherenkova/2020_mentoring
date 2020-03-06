package ua.mentoring.addressbook.Generators;

import org.testng.util.Strings;
import ua.mentoring.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    //генерация файлов
    List<GroupData> groups = generateGroups(count);

    //сохранение данных в файл
    save(groups, file);
  }

  private static  List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData()
              .withName(String.format("test %s",i))
              .withHeader(String.format("Header %s",i))
              .withFooter(String.format("Footer %s",i)));
      }
    return groups;
  }

  private static void save(List<GroupData> groups, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (GroupData group : groups) {
      writer.write(String.format("%s;%s;%s\n", group.getName(),group.getHeader(),group.getFooter()));
    }
    writer.close();
  }


}
