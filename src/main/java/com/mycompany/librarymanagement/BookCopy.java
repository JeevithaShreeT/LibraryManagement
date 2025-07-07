
package com.mycompany.librarymanagement;


public class BookCopy {

     private final int bookid;
     private final String bookcopyid;
     private boolean isbookborrowed;
     private int borroweduserid;
     private int racknum;
     private String duedate;
     
     public BookCopy(int bookid, String bookcopyid, int racknum){
         this.bookid = bookid;
         this.bookcopyid=bookcopyid;
         this.borroweduserid= -1;
         this.racknum=racknum;
         this.isbookborrowed=false;
         this.duedate="";
     }
     
     public int getBookid(){
         return this.bookid;
     }
     
     public String getBookcopyid(){
         return this.bookcopyid;
     }
     
     public int getracknum(){
         return this.racknum;
     }
     
     public boolean isBookBorrowed(){
         return this.isbookborrowed;
     }
     
     public int getBorroweduser(){
         return this.borroweduserid;
     }
     
     public String getDuedate(){
         return this.duedate;
     }
     
     public void setRacknum(int rackno){
         this.racknum = rackno;
     }
     
     public void setIsborrowed(boolean borrowed){
         this.isbookborrowed = borrowed;
     }
     
     public void setBorrowedUser(int user){
         this.borroweduserid = user;
     }
     
     public void setDuedate(String date){
         this.duedate = date;
     }
         
}
