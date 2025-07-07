

package com.mycompany.librarymanagement;

import java.util.List;
import java.util.Scanner;


public class LibraryManagement {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Library library = null;
        
        while(sc.hasNextLine()){
        
            String input = sc.nextLine().trim();
            
            if(input.equalsIgnoreCase("exit")){
                return;
            }
            
            String[] ip = input.split(" ");
            String command = ip[0];
            
            switch(command){
                case "create_library":
                    int libno = Integer.parseInt(ip[1]);
                    int rackno = Integer.parseInt(ip[2]);
                    library = new Library(libno, rackno);
                    System.out.println("created Library with :"+rackno+" racks");
                    break;
                    
                case "add_book":
                    int bookid = Integer.parseInt(ip[1]);
                    String title = ip[2];
                    List<String> authors = List.of(ip[3].split(","));
                    List<String> publishers = List.of(ip[4].split(","));
                    List<String> bookcopy = List.of(ip[5].split(","));
                    library.addBook(bookid, title, authors, publishers, bookcopy);
                    break;
                    
                case "remove_book_copy":
                    String bookcopyid = ip[1];
                    library.removeBook(bookcopyid);
                    break;
                    
                case "borrow_book":
                    int book_id = Integer.parseInt(ip[1]);
                    int userid = Integer.parseInt(ip[2]);
                    String date = ip[3];
                    library.borrowBook(book_id, userid, date);
                    break;
                    
                case "borrow_book_copy":
                    String bcid = ip[1];
                    int user_id = Integer.parseInt(ip[2]);
                    String due = ip[3];
                    library.borrowBookCopy(bcid, user_id, due);
                    break;
                    
                case "return_book_copy":
                    String bookcopy_id = ip[1];
                    library.returnBookCopy(bookcopy_id);
                    break;
                    
                case "print_borrowed":
                    int u_id = Integer.parseInt(ip[1]);
                    library.printBorrowedBook(u_id);
                    break;
                    
                case "search":
                    String attribute = ip[1];
                    String value = ip[2];
                    library.search(attribute, value);
                    break;
                           
            }
        }
    }
}
