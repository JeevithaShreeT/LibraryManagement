
package com.mycompany.librarymanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Library {
   
    private int rackno;
    private int libno;
    private Rack rack;
    private Map<Integer, Book> books;
    private Map<Integer, User> users;
    private Map<String, BookCopy> bookcopy;
    private Map<Integer, List<BookCopy>> bookcopies;

    public Library(int libno, int rackno){
       
       this.libno = libno;
       this.rackno = rackno; 
       rack = new Rack(rackno);
       books = new HashMap<>();
       users = new HashMap<>();
       bookcopy = new HashMap<>();
       bookcopies = new HashMap<>();
    }
    
    public void addBook(int bookid, String title, List<String>authors, List<String>publishers, List<String>bookcop){
        
        if(!books.containsKey(bookid)){
           Book b = new Book(bookid, title, authors, publishers);
           books.put(bookid, b);
       }
        
        List<BookCopy> copies = bookcopies.getOrDefault(bookid, new ArrayList<>());
        List<Integer> placedrack = new ArrayList<>();
        for(String copy : bookcop){
            
            int racknum = rack.findFirstAvailableRack();
            
            if(racknum == -1){
                System.out.println("No racks avaliable");
                return;
            }
            
            BookCopy bc = new BookCopy(bookid, copy, racknum);
            bookcopy.put(copy, bc);
            rack.addBook(bc);
            placedrack.add(racknum);
            copies.add(bc);
            
        }
        
        bookcopies.put(bookid, copies); 
        System.out.println("Added books to racks: ");
        
        System.out.print(placedrack.stream()
                             .map(String::valueOf)
                             .collect(Collectors.joining(",")));
        
    }
    
    public void removeBook(String bookcopyid){
        
        if(!bookcopy.containsKey(bookcopyid)){
            System.out.println("Invalid book id");
            return;
        }
        
        BookCopy bc = bookcopy.get(bookcopyid);
        int bookid = bc.getBookid();
        
        List<BookCopy> list = bookcopies.get(bookid);
        if(list != null){
            list.removeIf(b -> bc.getBookcopyid().equals(bookcopyid));
            bookcopies.put(bookid, list);
        }
        
        int num = rack.getRackNum(bookcopyid);
        boolean rem = rack.removeBook(bookcopyid);
        
        bookcopy.remove(bookcopyid);
        
        if(rem){
            System.out.println("Removes Book from rack: "+num);
        }
        else{
            System.out.println("Invalid book id");
        }
    }
    
    public void borrowBook(int bookid, int userid, String date){
        
        if(!books.containsKey(bookid)){
            System.out.println("Bookid is invalid");
            return;
        }
       
        User user = users.get(userid);
        if(user == null){
            
            User u = new User(userid, "user_"+ userid, new ArrayList<>());
            users.put(userid, u);
        }
        
        User u = users.get(userid);
        if(u.checkOverLimit()){
            System.out.println("OverLimit");
            return;
        }
        
        List<BookCopy> booklist = u.getBookList();
        List<BookCopy> bookscopies = bookcopies.get(bookid);
        for(BookCopy bc : bookscopies){
            if(!bc.isBookBorrowed()){
                
                bc.setIsborrowed(true);
                bc.setBorrowedUser(userid);
                u.addBorrowedBook(bc);
                bc.setDuedate(date);
                rack.removeBook(bc.getBookcopyid());
                
                System.out.println("Borrowed book from rack: "+rack.getRackNum(bc.getBookcopyid()));
                return;
            }
        }
        
        System.out.println("Book not avaliable");
        
    }
    
    public void borrowBookCopy(String bookid, int userid, String date){
        
        
        if(!bookcopy.containsKey(bookid)){
            System.out.println("Invalid BookCopy id");
            return;
        }
        
        BookCopy bc = bookcopy.get(bookid);
        
        if(bc.isBookBorrowed()){
            System.out.println("Book Not Avaliable");
        }
        
        
        User u = users.get(userid);
        if(u==null){
            User user = new User(userid, "user_"+userid, new ArrayList<>());
            users.put(userid, user);
        }
        
        
        if(u.checkOverLimit()){
            System.out.println("OverLimit");
            return;
        }
        
        
        u.addBorrowedBook(bc);
        bc.setBorrowedUser(userid);
        bc.setDuedate(date);
        bc.setIsborrowed(true);
        int num = bc.getracknum();
        rack.removeBook(bookid);
        
        System.out.println("Borrowed BookCopy from rack: "+ num);
        
    }
    
    public void returnBookCopy(String bookcopyid){
        
        if(!bookcopy.containsKey(bookcopyid)){
            System.out.println("Invalid book id");
            return;
       }
        
        BookCopy bc = bookcopy.get(bookcopyid);
        
        if(!bc.isBookBorrowed()){
            System.out.println("Invalid Book id");
            return;
        }
        
        int user = bc.getBorroweduser();
        User u = users.get(user);
        
        u.removeBook(bc);
        
        bc.setIsborrowed(false);
        
        rack.addBook(bc);
        int num = rack.getRackNum(bookcopyid);
        bc.setRacknum(num);
        bc.setBorrowedUser(-1);
        bc.setDuedate("");
        
        System.out.println("Returned bookcopy: "+bookcopyid+ "added to the rack: "+num);
    }
    
    public void printBorrowedBook(int userid){
        
        if(!users.containsKey(userid)){
            System.out.println("Invalid user");
            return;
        }
        
        User user = users.get(userid);
        List<BookCopy> list = user.getBookList();
        
        if(list==null || list.isEmpty()){
            return;
        }
        
        list.sort(Comparator.comparing(BookCopy::getBookcopyid));
        for(BookCopy bc : list){
            System.out.println("BookCopy: "+bc.getBookcopyid()+" Duedate: "+bc.getDuedate());
        }
    }
    
    public void search(String attribute, String value){
        
         List<BookCopy> res = new ArrayList<>();
         Map<Integer, Book> matchedBooks = new HashMap<>();

    for (Book b : books.values()) {
        boolean match = switch (attribute) {
            case "book_id" -> String.valueOf(b.getBookid()).equals(value);
            case "author" -> b.getAuthors().contains(value);
            case "publisher" -> b.getPublishers().contains(value);
            default -> false;
        };

        if (match) {
            matchedBooks.put(b.getBookid(), b);
            List<BookCopy> copies = bookcopies.get(b.getBookid());
            if (copies != null) res.addAll(copies);
        }
    }

    res.sort(Comparator.comparingInt(bc -> bc.isBookBorrowed() ? Integer.MAX_VALUE : bc.getracknum()));

    for (BookCopy bc : res) {
        Book book = matchedBooks.get(bc.getBookid());
        String authors = String.join(",", book.getAuthors());
        String publishers = String.join(",", book.getPublishers());

        if (bc.isBookBorrowed()) {
            System.out.println("Book Copy: " + bc.getBookcopyid() + " " +
                    bc.getBookid() + " " + book.getTitle() + " " +
                    authors + " " + publishers + " -1 " +
                    bc.getBorroweduser() + " " + bc.getDuedate());
        } else {
            System.out.println("Book Copy: " + bc.getBookcopyid() + " " +
                    bc.getBookid() + " " + book.getTitle() + " " +
                    authors + " " + publishers + " " + bc.getracknum());
        }
    }
    }
    
    
        
}
