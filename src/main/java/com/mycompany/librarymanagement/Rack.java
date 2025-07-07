
package com.mycompany.librarymanagement;


import java.util.HashMap;
import java.util.Map;


public class Rack {
    
    private final int num;
    private Map<Integer, BookCopy> racks;
    
    
    public Rack(int num){
        this.num = num;
        racks = new HashMap<>();
        for(int i=0; i<num; i++){
            racks.put(i, null);
        }
    }
     
   public boolean findBook(BookCopy copy){
        for(BookCopy b : racks.values()){
            if(b.equals(copy)){
                return true;
            }
        }
        
        return false;
   }
   
   public int findFirstAvailableRack(){
       for(int k : racks.keySet()){
           if(racks.get(k)==null){
               return k;
           }
       }
       
       return -1;
       
   }
   
   public boolean checkRackAvaliablity(int rackno){
       if(racks.get(rackno)==null){
           return true;
       }
       return false;
   }
   
   public boolean checkBookAvailabilty(BookCopy book){
       for(BookCopy b : racks.values()){
           if(b!=null && b.getBookcopyid().equals(book.getBookcopyid()) && !b.isBookBorrowed()){
               return true;
           }
       }
       return false;
   }
   
   public void addBook(BookCopy book){
       for(int k : racks.keySet()){
           if(racks.get(k)==null){
               racks.put(k, book);
               book.setRacknum(k);
               return;
           }
       }
   }
   
   public boolean removeBook(String bookid){
       for(int k: racks.keySet()){
           BookCopy copy = racks.get(k);
           if(copy!=null && copy.getBookcopyid().equals(bookid)){
               racks.put(k, null);
               return true;
           }
           
       }
       
       return false;
   }
   
   public int getRackNum(String bookid){
       for(Map.Entry<Integer, BookCopy> entry : racks.entrySet()){
           if(entry.getValue() != null && entry.getValue().getBookcopyid().equals(bookid)){
               return entry.getKey();
           }
       }
       return -1;
   }
    
}
