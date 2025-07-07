
package com.mycompany.librarymanagement;

import java.util.ArrayList;
import java.util.List;



public class User {
     
     private final int userid;
     private final String name;
     private List<BookCopy> books;
     
     public User(int userid, String name,  List<BookCopy> books){
         this.userid= userid;
         this.name=name;
         this.books = books;
     }
     
     public int getUserid(){
         return this.userid;
     }
     
     public String getUsername(){
         return this.name;
     }
     
     public List<BookCopy> getBookList(){
         return books;
     }
     
     public boolean checkOverLimit(){
         if(books.size() >= 5) return true;
         
         return false;
     }
     
     public void addBorrowedBook(BookCopy copy){
         books.add(copy);
     }
     
     public void removeBook(BookCopy copy){
            
           books.removeIf(b -> b.getBookcopyid().equals(copy.getBookcopyid()));
         
     }
     
     
     
}
