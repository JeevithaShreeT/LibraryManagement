
package com.mycompany.librarymanagement;

import java.util.List;
import java.util.Map;


public class Book {
     
      private final int bookid;
      private final String title;
      private List<String> authors;
      private List<String> publishers;
      private boolean isbookborrowed;
      //private Map<Integer, String> bookcopies;
      
      public Book(int bookid, String title, List<String>authors, List<String>publishers){
          this.bookid = bookid;
          this.title = title;
          this.authors=authors;
          this.publishers=publishers;
          this.isbookborrowed = false;
      }
      
      
      public int getBookid(){
          return this.bookid;
      }
      
      public String getTitle(){
          return this.title;
      }
      
      public List<String> getAuthors(){
          return this.authors;
      }
      
      public List<String> getPublishers(){
          return this.publishers;
      }
      
      public boolean isbookborrowed(){
          return this.isbookborrowed;
      }
      
      public void setAuthors(List<String> author){
          this.authors = author;
      }
      
      public void setPublishers(List<String> publisher){
          this.publishers = publisher;
      }
      
      public void setBookborrowed(boolean borrow){
          this.isbookborrowed= borrow;
      }
      
      @Override
      public String toString(){
          return "Book Id: "+bookid+" Book Title: "+title+" Authors: "+String.join(",", authors)+" Publishers: "+String.join(",", publishers);
      }
}
