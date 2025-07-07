# Library Management System

This is a command-line based Library Management System implemented in Java. It is designed following object-oriented programming and low-level design principles. The system handles the core functionalities required in a library such as book cataloging, user management, borrowing and returning of book copies, and rack allocation.

---

## Project Overview

This project simulates a library with the following components:

- Books with metadata such as title, authors, and publishers.
- Physical copies of books, each identified uniquely and placed on racks.
- Racks to store book copies, where each rack holds one book copy.
- Users who can borrow and return books, with a maximum borrow limit enforced.
- A search mechanism to find books by ID, author, or publisher.
- Full support for standard library operations through structured commands.

---

## Features

- Create a library with a specified number of racks.
- Add new books with multiple authors, publishers, and multiple unique book copies.
- Allocate each book copy to the first available rack.
- Borrow books either by book ID (first available copy) or by a specific book copy ID.
- Enforce borrowing limits (a user can borrow a maximum of 5 books).
- Return a borrowed book copy and reassign it to the first available rack.
- Remove a book copy from the system.
- Search for books by book ID, author, or publisher.
- Display all borrowed books for a user, sorted by book copy ID.

---

## Class Structure

- `Book`: Represents the metadata of a book.
- `BookCopy`: Represents a physical copy of a book with a unique identifier, rack information, and borrowing status.
- `User`: Represents a library user, tracks the books they have borrowed.
- `Rack`: Manages the rack numbers and placement of book copies.
- `Library`: Coordinates the overall logic between books, book copies, users, and racks.
- `LibraryManagement`: The main class that reads and parses commands, and invokes corresponding operations on the library.

---

## Technology Stack

- Java SE 17

- Standard Java collections and command-line I/O

- Designed using core object-oriented principles

- No external dependencies


## Author

- [Jeevitha Shree T](https://github.com/JeevithaShreeT)
