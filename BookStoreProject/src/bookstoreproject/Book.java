/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreproject;

/**
 *
 * @author user
 */
public class Book {
    private String bookName;
    private String publisher;
    private int amount;
    private double price;
    private String modifyDate;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Book(String bookName, String publisher, int amount, String modifyDate, double price) {
        this.bookName = bookName;
        this.publisher = publisher;
        this.amount = amount;
        this.price = price;
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return String.format("%s\t%18s\t%7d\t%20s\t%10.2f%n", bookName, publisher, amount, modifyDate, price);
    }
    public String  writeString(){
        return String.format("%s,%s,%d,%s,%.2f\r\n", bookName, publisher, amount, modifyDate, price);
    }
}
