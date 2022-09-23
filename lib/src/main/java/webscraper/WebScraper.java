package webscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
*
* @author Tobias Nordwall
* @version Java 11.0.15
*/

/**
    * The visitedUrls list stores the url of the visited pages, so that the same site is not scraped more than once
    The savedData list stores the result of the scrape on the current site for each recursion
    The breakPoint variable startes at 0 and is increased by 1 for each recursion until it reaches 25 and breaks the loop. 
    The occurences variable stores the numbers of occurences for the searched word for every recursion, once the count method has compared the scraped text data with the searched word. 
    */

public class WebScraper {
    List<String> visitedUrls = new ArrayList<String>();
    List<String> savedData = new ArrayList<String>();
    int breakPoint = 0;
    int occurences = 0;

    /**
     * 
     * @param url accepts a string of the url that is going to be scraped/crawled
     * @param word accepts a string with the word that should be searched for
     * @return the number of the occurences of the word 
     */

    public int webScraper (String url, String word) {
        try {
            crawlAndScrape(url, word, 25);
        } catch (Exception e) {
            e.getStackTrace();
        }
            DetailedWordOccurences.addStrings(savedData);
            return occurences;
    }
    
    /**
     * 
     * @param url accepts a string of the url that is going to be scraped/crawled
     * @param word accepts a string with the word that should be searched for
     * @param threshold accepts a the breakingpoint threshold for the crawler (how deep it will go). The option to change it in the method is only for testing, since it cannot be changed in the GUI. 
     * @return returns numbers of occurences that we use to test the method 
     * @throws IOException if error occurs
     */

    public int crawlAndScrape(String url, String word, int threshold) throws IOException {
        String words = ""; 

        try {
            Document doc = Jsoup.connect(url).get();
            words = doc.text();

            if(!visitedUrls.contains(url)) {
                visitedUrls.add(url);
                visitedUrls.add(url+"/");
                visitedUrls.add(url+"#");

                savedData.add("The word " + word + " occured " + count(words, word) + " times on the site " +url);
    
                System.out.println("The word " + word + " occured " + count(words, word) + " times on the site " +url);

                occurences += count(words, word);
                breakPoint++;
                System.out.println(breakPoint);

                Elements nextLinks = doc.select("a[href]");
                for( Element next : nextLinks) {
                    if(breakPoint >= threshold ) {
                        break;
                    }
                    crawlAndScrape(next.absUrl("href"), word, threshold);
                } 
        } 
        } catch (Exception e) {
        e.getStackTrace();
        }
        return occurences;
    }

    /**
     * 
     * @param words accepts a string of the words that has been scraped in the last recursion 
     * @param word accepts a string with the searched word that will be compared to words
     * @return returns an int with the numbers of occursions of the word in words 
     */

    public static int count(String words, String word) {
        words = words.toLowerCase();
        word = word.toLowerCase();
        Pattern p = Pattern.compile(word);
        Matcher m = p.matcher(words);
        int count = 0;
        while (m.find()){
            count +=1;
        }  
        return count; 
    }
    
} 
