package ua.mentoring.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args){
 //   String[] langs = new String[4];
    String[] langs = {"Java","C#","Python","PHP"};
 //   langs[0] = "Java";
 //   langs[1] = "C#";
 //   langs[2] = "Python";
 //   langs[3] = "PHP";

    for (int i = 0; i < langs.length; i++)
      System.out.println("Я хочу выучить " + langs[i]);

    for (String i : langs)
      System.out.println("Я хочу выучить " + i);



// arrayList
    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");
    languages.add("PHP");

    for (String i : languages)
      System.out.println("Я хочу выучить " + i);


    // array as list initialization
    List<String> languagesFromArray = Arrays.asList("Java","C#","Python","PHP");

    for (int i = 0; i < languagesFromArray.size(); i++) {
      System.out.println("Я хочу выучить " + languagesFromArray.get(i));
    }
  }
}
