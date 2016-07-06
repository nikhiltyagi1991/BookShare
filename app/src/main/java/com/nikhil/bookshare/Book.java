package com.nikhil.bookshare;

/**
 * Created by nikhil on 30/5/16.
 */
public class Book {
    private String name,publisher,author,imageUrl;
    private double maxPrice,expectedPrice;

    public Book(String name,String author,double maxPrice,double expectedPrice){
        this.name = name;
        this.author = author;
        this.maxPrice = maxPrice;
        this.expectedPrice = expectedPrice;
    }

    public Book(String name,String author,String publisher,String imageUrl,double maxPrice,double expectedPrice){
        this.name = name;
        this.author = author;
        this.maxPrice = maxPrice;
        this.expectedPrice = expectedPrice;
        this.publisher = publisher;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthor() {
        return author;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public double getExpectedPrice() {
        return expectedPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setExpectedPrice(double expectedPrice) {
        this.expectedPrice = expectedPrice;
    }
}
