package webscraper;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.*;  

/**
*
* @author Tobias Nordwall
* @version Java 11.0.15
*/


public class WebScraperGUI {
    private static JLabel label1; 
    private static JTextField userText1; 
    private static JLabel label2; 
    private static JTextField userText2; 
    private static JButton scrapeButton; 
    private static JButton printButton; 
    private static JLabel result; 

    /**
    * 
    * The main method for the WebScraper graphical interface
    */

    public static void main(String[] args) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.add(panel);

        panel.setLayout(null);

        label1 = new JLabel("Website url");
        label1.setBounds(200, 125, 80, 25);
        panel.add(label1);

        userText1 = new JTextField(20);
        userText1.setBounds(200, 100, 200, 25);
        panel.add(userText1);

        label2 = new JLabel("SearchWord");
        label2.setBounds(200, 200, 80, 25);
        panel.add(label2);

        userText2 = new JTextField(20);
        userText2.setBounds(200, 175, 200, 25);
        panel.add(userText2);

        scrapeButton = new JButton("Scrape");
        scrapeButton.setBounds(260, 250, 80, 25);
        scrapeButton.setBackground(Color.BLACK);
        scrapeButton.setForeground(Color.white);
        panel.add(scrapeButton);

        /**
        * 
        * Anonymous method that handles the click event for the scrapeButton and that calls the webscraper
        the printButton is turned to visible if the scrape is succesfull 
        */
    
        scrapeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = userText1.getText();
                String input2 = userText2.getText();
                WebScraper webScraper = new WebScraper();

                try {
                    result.setText("The word occured " + webScraper.webScraper(input1, input2) + " times"); 
                    printButton.setVisible(true);
                    
                } catch (Exception ex) {
                    System.out.println(ex.getStackTrace());
                    result.setText("Error : " + ex.getStackTrace());
                }    
            }
        });

        printButton = new JButton("Save results to textfile");
        printButton.setBounds(200, 400, 200, 25);
        printButton.setBackground(Color.BLACK);
        printButton.setForeground(Color.white);
        panel.add(printButton);
        printButton.setVisible(false);

        /**
        * 
        * Anonymous method that handles the click event for the printButton and that call the detailedWordOccurences method saveToFile, that writes the information of the current crawl/scrape
        */

        printButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DetailedWordOccurences detailedWordOccurences = new DetailedWordOccurences();
                    detailedWordOccurences.saveToFile();
                    
                } catch (Exception ex) {
                    System.out.println(ex.getStackTrace());
                    result.setText("Error : " + ex.getStackTrace());
                }    
            }
        });
        result = new JLabel("");
        result.setBounds(200, 325, 200, 25);
        panel.add(result);

        frame.setVisible(true);
    }
   
}
