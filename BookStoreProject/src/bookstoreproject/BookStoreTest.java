package bookstoreproject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookStoreTest {

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {

        Scanner sc = new Scanner(System.in);
        String choice, manage, buy, account, password;
        BookStore store = new BookStore();
        do {
            System.out.print("please input your account(admin or user),input exit if you want to exit: ");
            account = sc.next();

//            showMenu();
            //choice = sc.next();
//            if (choice.matches("[0-2]{1}")) {
            if (account.equals("admin")) {
                System.out.print("please input your password: ");
                password = sc.next();
                do {
                    manageList();
                    manage = sc.next();
                    switch (manage) {
                        case "1":
                            store.showBook();
                            store.addBook();
                            store.showBook();
                            store.writeFile();
                            break;
                        case "2":
                            store.showBook();
                            store.updateBook();
                            store.showBook();
                            store.writeFile();
                            break;
                        case "3":
                            store.showBook();
                            store.removeBook();
                            store.showBook();
                            store.writeFile();
                            break;
                        case "4":
                            store.showBook();
                            store.queryStock();
                            break;
                        case "0":
                            break;
                        default:
                            System.out.println("Invalid Format");
                    }
                } while (!manage.equals("0"));
            } else if (account.equals("user")) {
                List<List<String>> buyRec = new ArrayList<>();
                do {
                    buyList();
                    System.out.print("please input :");
                    buy = sc.next();
                    switch (buy) {
                        case "1":
                            store.showBook();
                            store.buyBook(buyRec);
                            break;
                        case "2":
                            printBoughtBooks(buyRec);
                            break;
                        case "3":
                            store.checkOutBook();
                            break;
                        case "0":
                            break;
                        default:
                            System.out.println("Invalid Format");
                    }
                    if (buy.equals("0")) {
                        break;
                    }
                } while (true);
            }else if(account.equals("exit")){
                break;
            }
//            } else {
//                System.out.println("Invalid Format");
//            }
        } while (true);
    }

    public static void showMenu() {
        System.out.println("************************");
        System.out.println("Welcome to Bookstore");
        System.out.println("1. manage book");
        System.out.println("2. buy book");
        System.out.println("0. exit");
        System.out.println("");
        System.out.println("************************");
        System.out.print("please choice:");
    }

    public static void manageList() {
        System.out.println("************************");
        System.out.println("manage book");
        System.out.println("1. add book");
        System.out.println("2. update book");
        System.out.println("3. remove book");
        System.out.println("4. query stock");
        System.out.println("0. exit");
        System.out.println("************************");
        System.out.print("please input:");
    }

    public static void buyList() {
        System.out.println("********** book system *********");
        System.out.println("What do you want to do");
        System.out.println("1 buy book");
        System.out.println("2 print record");
        System.out.println("3 check out");
        System.out.println("0 exit");
        System.out.println("******************************");
    }
    
    public static void printBoughtBooks(List<List<String>> buyRec){
        if(buyRec.size() == 0){
            System.out.println("You have not bought any books");
            return;
        }
        for(List<String> i :buyRec){
            System.out.println(i.get(0) + " " + i.get(1));
        }
    }

}
