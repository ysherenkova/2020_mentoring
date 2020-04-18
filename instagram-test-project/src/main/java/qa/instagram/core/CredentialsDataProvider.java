package qa.instagram.core;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CredentialsDataProvider {

  @DataProvider(name = "credentials-data-provider")
  private Object[] parseCsvData() throws IOException {
    File file = new File("/Users/yuliia_sherenkova/Documents/GitHub/2020_mentoring/instagram-test-project/src/main/resources/credentials.csv");
    try (BufferedReader input = new BufferedReader(new FileReader(file))) {
      String line = null;
      ArrayList<Credentials> data = new ArrayList<>();
      while ((line = input.readLine()) != null) {
        String in = line.trim();
        String[] temp = in.split("\\|");
        Credentials cred = new Credentials(temp[0], temp[1]);
        System.out.println(cred);
        data.add(cred);
      }
      return data.toArray();
    }
  }
}
