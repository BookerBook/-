/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class BookStore {
    String file_in = "bookstore.txt";
    String file_out = "buylist.txt";
    Path file_in_path = Paths.get(file_in);
    Path file_out_path = Paths.get(file_out);
    private int count;
    private Book[] book = new Book[100];
    Scanner sc = new Scanner(System.in);
    String[][] list = new String[100][4];

    public void readFile() {
        if (Files.exists(file_in_path)) {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file_in), "big5"));
                StringBuffer buffer = new StringBuffer();
                String temp;
                count = 0;
                while ((temp = input.readLine()) != null) {
                    buffer.append(temp + "@");
                    count++;
                }
                input.close();
                String[] str1 = String.valueOf(buffer).split("@");
                for (int i = 0; i < str1.length; i++) {
                    String[] str2 = str1[i].split(",");
                    book[i] = new Book(str2[0], str2[1], Integer.parseInt(str2[2]), str2[3], Double.parseDouble(str2[4]));
                }
            } catch (IOException e) {
                System.err.println("reading from file fails");
            }
        } else {
            System.out.println(file_in_path + "does not exist");
        }
    }

    public BookStore() throws FileNotFoundException, UnsupportedEncodingException, IOException {
        readFile();
    }

    public void showBook() {
        if (Files.exists(file_in_path)) {
            System.out.printf("%s\t%s\t%7s\t%20s\t%10s%n", "bookName", "publisher", "amount", "lastModifiedDate", "price");
            for (int i = 0; i < count; i++) {
                if (book[i] != null) {
                    System.out.print(book[i].toString());
                }
            }
        } else {
            System.out.println(file_in_path + "does not exist");
        }
    }

    public void writeFile() {
        if (Files.exists(file_in_path)) {
            try {
                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file_in), "big5"));
                for (int i = 0; i < count; i++) {
                    if (book[i] != null) {
                        output.write(book[i].writeString());
                    }
                }
                output.close();
            } catch (IOException e) {
                System.err.println("writing to file fails");
            }
        } else {
            System.out.println(file_in_path + "does not exist");
        }
    }

    public void addBook() {
        if (Files.exists(file_in_path)) {
            do {
                String bookName, publisher, amount, modifyDate, price;
                Date now = new Date();
                int hasCurrent = 0;
                modifyDate = String.format("%tF", now);
                modifyDate = modifyDate.replace("-", "/");
                System.out.print("please input book name:");
                bookName = sc.next();
                for (int i = 0; i < count; i++) {
                    if (book[i] != null) {
                        if (book[i].getBookName().equals(bookName)) {
                            hasCurrent = 1;
                            System.out.println(bookName + " has existed");
                            break;
                        }
                    }
                }
                if (hasCurrent == 0) {
                    System.out.print("please input publisher:");
                    publisher = sc.next();
                    System.out.print("please input amount:");
                    amount = sc.next();
                    System.out.print("please input price:");
                    price = sc.next();
                    if (amount.matches("\\d+") && price.matches("\\d+[.]?\\d*") && Integer.parseInt(amount) > 0 && Double.parseDouble(price) > 0) {
                        book[count] = new Book(bookName, publisher, Integer.parseInt(amount), modifyDate, Double.parseDouble(price));
                        count++;
                        System.out.println("add success!!");
                        break;
                    } else {
                        System.out.println("Invalid Format");
                    }
                }
            } while (true);
        } else {
            System.out.println(file_in_path + "does not exist");
        }
    }

    public void updateBook() {
        if (Files.exists(file_in_path)) {
            do {
                String bookName, publisher, amount, modifyDate, price;
                Date now = new Date();
                int hasCurrent = 0, updateSuccess = 0;
                modifyDate = String.format("%tF", now);
                modifyDate = modifyDate.replace("-", "/");
                System.out.print("please input book name:");
                bookName = sc.next();
                for (int i = 0; i < count; i++) {
                    if (book[i] != null) {
                        if (book[i].getBookName().equals(bookName)) {
                            hasCurrent = 1;
                            System.out.print("please input new publisher:");
                            publisher = sc.next();
                            System.out.print("please input new amount:");
                            amount = sc.next();
                            System.out.print("please input new price:");
                            price = sc.next();
                            if (amount.matches("\\d+") && price.matches("\\d+[.]?\\d*") && Integer.parseInt(amount) > 0 && Double.parseDouble(price) > 0) {
                                book[i].setPublisher(publisher);
                                book[i].setAmount(Integer.parseInt(amount));
                                book[i].setModifyDate(modifyDate);
                                book[i].setPrice(Double.parseDouble(price));
                                updateSuccess = 1;
                                System.out.println("update success!!");
                            } else {
                                System.out.println("Invalid Format");
                            }
                            break;
                        }
                    }
                }
                if (hasCurrent == 0) {
                    System.out.println(bookName + " does not exist");
                } else if (hasCurrent == 1 && updateSuccess == 1) {
                    break;
                }
            } while (true);
        } else {
            System.out.println(file_in_path + "does not exist");
        }
    }

    public void removeBook() {
        if (Files.exists(file_in_path)) {
            do {
                String bookName;
                int hasCurrent = 0;
                System.out.print("please input book name:");
                bookName = sc.next();
                for (int i = 0; i < count; i++) {
                    if (book[i] != null) {
                        if (book[i].getBookName().equals(bookName)) {
                            hasCurrent = 1;
                            book[i] = null;
                            System.out.println("remove success");
                            break;
                        }
                    }
                }
                if (hasCurrent == 0) {
                    System.out.println(bookName + " does not exist");
                } else {
                    break;
                }
            } while (true);
        } else {
            System.out.println(file_in_path + "does not exist");
        }
    }

    public void buyBook(List<List<String>> buyRec) {
        if (Files.exists(file_in_path)) {
            do {
                String bookName, amount;
                buyRec.add(new ArrayList<String>());
                int hasCurrent = 0, hasInList = 0, buySuccess = 0;
                System.out.print("please input book name :");
                bookName = sc.next();
                buyRec.get(buyRec.size() - 1).add(bookName);
                for (int i = 0; i < book.length; i++) {
                    if (book[i] != null) {
                        if (book[i].getBookName().equals(bookName)) {
                            hasCurrent = 1;
                            System.out.println("please input amount you want to buy :");
                            
                            amount = sc.next();
                            if(Integer.valueOf(amount) > book[i].getAmount()){
                                System.out.print("deficiency");
                                buyRec.remove(buyRec.size() - 1);
                                return;
                            }
                            book[i].setAmount(book[i].getAmount() - Integer.valueOf(amount));
                            writeFile();
                            buyRec.get(buyRec.size() - 1).add(amount);
                            if (amount.matches("\\d+")) {
                                int index = 0;
                                for (int j = 0; j < list.length; j++) {
                                    if (list[j][0] != null) {
                                        index++;
                                        if (list[j][0].equals(bookName)) {
                                            hasInList = 1;
                                            list[j][1] = String.valueOf(Integer.parseInt(list[j][1]) + Integer.parseInt(amount));
                                            list[j][3] = String.valueOf(Double.parseDouble(list[j][3]) + Integer.parseInt(amount) * book[i].getPrice());
                                            break;
                                        }
                                    }
                                }
                                if (hasInList == 0) {
                                    list[index][0] = bookName;
                                    list[index][1] = amount;
                                    list[index][2] = String.valueOf(book[i].getPrice());
                                    list[index][3] = String.valueOf(Integer.parseInt(amount) * book[i].getPrice());
                                }
                                buySuccess = 1;
                                System.out.println("buy success");
                                break;
                            } else {
                                System.out.println("Invalid Format");
                            }
                        }
                    }
                }
                if (hasCurrent == 0) {
                    System.out.println(bookName + " does not exist");
                }
                if (buySuccess == 1) {
                    break;
                }
            } while (true);
        } else {
            System.out.println(file_in_path + "does not exist");
        }
    }

    public void checkOutBook() {
        if (Files.exists(file_in_path)) {
            if (Files.exists(file_out_path)) {
                int len = 0;
                for (int i = 0; i < list.length; i++) {
                    if (list[i][0] != null) {
                        len++;
                    }
                }
                String[][] sort = new String[len][4];
                for (int i = 0; i < sort.length; i++) {
                    for (int j = 0; j < 4; j++) {
                        sort[i][j] = list[i][j];
                    }
                }
                for (int i = len; i > 1; i--) {
                    for (int j = 0; j < len - 1; j++) {
                        if (Double.parseDouble(sort[j][3]) < Double.parseDouble(sort[j + 1][3])) {
                            String[] temp = new String[4];
                            for (int k = 0; k < temp.length; k++) {
                                temp[k] = sort[j][k];
                                sort[j][k] = sort[j + 1][k];
                                sort[j + 1][k] = temp[k];
                            }
                        }
                    }
                }
                try {
                    BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file_out), "big5"));
                    for (int i = 0; i < sort.length; i++) {
                        String temp = String.format("%s,%s,%s,%s\r\n", sort[i][0], sort[i][1], sort[i][2], sort[i][3]);
                        output.write(temp);
                    }
                    output.close();
                    System.out.println("check out success !!");
                } catch (IOException e) {
                    System.err.println("writing to file fails");
                }
            } else {
                System.out.println(file_out_path + "does not exist");
            }
        } else {
            System.out.println(file_in_path + "does not exist");
        }
    }
    
    public void queryStock(){
        if (Files.exists(file_in_path)) {
                String bookName;
                System.out.print("please input book name:");
                bookName = sc.next();
                for (int i = 0; i < count; i++) {
                    if (book[i] != null) {
                        if (book[i].getBookName().equals(bookName)) {
                            System.out.println("There are " + book[i].getAmount()+" "+ book[i].getBookName() +" in our ware house");
                            return;
                        }
                    }
                }
                System.out.println("We cannot find this book in our ware house");
        } else {
            System.out.println(file_in_path + "does not exist");
        }
    }
}

