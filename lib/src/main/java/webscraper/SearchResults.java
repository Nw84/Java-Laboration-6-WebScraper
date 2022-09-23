package webscraper;

public interface SearchResults {
    public void saveToFile();

    //I chose to create an interface here since i might want to add other objects to scrape for in the future and i want them all to share certain methods, like the saveToFile method. 

}
