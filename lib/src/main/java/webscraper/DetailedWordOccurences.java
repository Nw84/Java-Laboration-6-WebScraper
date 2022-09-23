package webscraper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
*
* @author Tobias Nordwall
* @version Java 11.0.15
*/

public class DetailedWordOccurences implements SearchResults {
    private static List<String> stringList = new ArrayList<>();

    /**
    * List that will store the scraped data from the webscraper
    */

    /**
    * @param string Accepts a string and adds it to the stringList
    */

    public static void addString(String string) {
        stringList.add(string);
    }

    /**
    * @param strings Accepts a collection of strings and adds them all to the stringList
    */

    public static void addStrings(Collection<String> strings) {
      stringList.addAll(strings);
    }

     /**
    * @return Returns the list of the strings as a collections unmodifiableList
    */

    public List<String> getStringList() {
        return Collections.unmodifiableList(stringList);
    }

    /**
    * Method that creates a file with the name of the current date and writes the content of the stringList on a new line for each iteration
    */

    @Override
    public void saveToFile() {
      LocalDate currentdate = LocalDate.now();

        try {
            File myObj = new File(currentdate +".txt");

            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              System.out.println("File already exists.");
            }
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        try {
            FileWriter myWriter = new FileWriter(currentdate +".txt");
            for (int i = 0; i < getStringList().size(); i++) {
              myWriter.write(getStringList().get(i) + "\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");  
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    } 
}
