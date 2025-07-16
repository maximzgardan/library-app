import java.util.*;

// Book class to represent a book
class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return title + " by " + author;
    }
}

// User class to represent a library user
class User {
    private String name;
    private List<Book> borrowedBooks;

    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}

// Library class to manage books and users
class Library {
    private List<Book> catalog;
    private List<User> users;

    public Library() {
        this.catalog = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    /**
     * Adds a new book to the library catalog.
     *
     * @param title  the title of the book to add
     * @param author the author of the book to add
     */
    public void addBook(String title, String author) {
        catalog.add(new Book(title, author));
        System.out.println("Added: " + title + " by " + author);
    }

    /**
     * Allows a user to borrow a book from the library.
     *
     * If the user does not exist, a new user is created and added to the library.
     * If the book exists and is available, it is marked as borrowed by the user.
     * Otherwise, prints an error message indicating the book is not available or not found.
     *
     * @param userName   the name of the user borrowing the book
     * @param bookTitle  the title of the book to be borrowed
     */
    public void borrowBook(String userName, String bookTitle) {
        String errorMessage = "User %s can\'t borrow the book %s.Book not available or not found.";
        User user = findUser(userName);
        Book book = findBook(bookTitle);

        if (user == null) {
            user = new User(userName);
            users.add(user);
        }

        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            user.borrowBook(book);
            System.out.println(userName + " borrowed: " + bookTitle);
        } else {
            System.out.printf((errorMessage) + "%n", userName,  bookTitle);
        }
    }

    /**
     * Allows a user to return a borrowed book to the library.
     *
     * Checks if the user and book exist and if the user has borrowed the book.
     * If so, marks the book as available and removes it from the user's borrowed books.
     * Prints a confirmation message or an error if the operation is invalid.
     *
     * @param userName   the name of the user returning the book
     * @param bookTitle  the title of the book to be returned
     */

    public void returnBook(String userName, String bookTitle) {
        User user = findUser(userName);
        Book book = findBook(bookTitle);

        if (user != null && book != null && user.getBorrowedBooks().contains(book)) {
            book.setAvailable(true);
            user.returnBook(book);
            System.out.println(userName + " returned: " + bookTitle);
        } else {
            System.out.println("Book or user not found, or book not borrowed by user.");
        }
    }

    /**
     * Prints a list of books borrowed by a specific user.
     *
     * If the user exists and has borrowed books, prints the user's name and the list of borrowed books.
     * Otherwise, prints a message indicating that the user has no borrowed books or was not found.
     *
     * @param userName the name of the user whose borrowed books are to be printed
     */
    public void printBorrowedBooks(String userName) {
        User user = findUser(userName);
        if (user != null && !user.getBorrowedBooks().isEmpty()) {
            System.out.println(userName + "'s borrowed books:");
            for (Book book : user.getBorrowedBooks()) {
                System.out.println("- " + book);
            }
        } else {
            System.out.println(userName + " has no borrowed books or user not found.");
        }
    }

    /**
     * Finds a book in the library catalog by its title, ignoring case.
     *
     * @param title the title of the book to search for
     * @return the Book object if found, or null if not found
     */
    private Book findBook(String title) {
        for (Book book : catalog) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Finds a user in the library by their name, ignoring case.
     *
     * @param name the name of the user to search for
     * @return the User object if found, or null if not found
     */
    private User findUser(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }
}

/****************** Library Kata *******************/

//You can use any language you like; when in doubt choose the SIMPLEST option

//I would like following features in my library application

//1. Add a new book to the catalog
//2. A user can borrow a book
//3. A user can return a book
//4. Print a list of the books that I borrowed

/*******************************************************/

public class LibraryApp {
    public static void main(String[] args) {
        Library library = new Library();
        // 1. Add a new book to the catalog
        library.addBook("OCA: Oracle Certified Associate Java SE 8", "Jeanne");
        library.addBook("1984", "George");
        library.addBook("Java Game", "Lee");
        //2. A user can borrow a book
        library.borrowBook("Maxim", "OCA: Oracle Certified Associate Java SE 8");
        library.borrowBook("Paul", "1984");
        library.borrowBook("Simon", "1984"); // Should fail (not available)

        library.printBorrowedBooks("Maxim");
        //3. A user can return a book
        library.returnBook("Maxim", "OCA: Oracle Certified Associate Java SE 8");
        //4. Print a list of the books that I borrowed
        library.printBorrowedBooks("Maxim");

        library.borrowBook("Simon", "1984");
        library.printBorrowedBooks("Simon");
    }
}