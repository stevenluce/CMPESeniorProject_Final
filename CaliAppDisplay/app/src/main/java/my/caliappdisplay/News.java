package my.caliappdisplay;

public class News {
    private String title;
    private String description;
    private String author;
    private String url;
    private String urlToImage;

    public News(){ } //empty constructor

    public News(String title, String description, String author, String url, String urlToImage) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.url = url;
        this.urlToImage = urlToImage;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getAuthor() { return author; }
    public String geturl() { return url; }
    public String getUrlToImage() { return urlToImage; }
}
