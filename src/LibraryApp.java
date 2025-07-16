import com.max.domain.application.LibraryService;

public class LibraryApp {
    public static void main(String[] args) {
        LibraryService service = new LibraryService();

        service.addBook("OCA: Oracle Certified Associate Java SE 8", "Jeanne");
        service.addBook("1984", "George");
        service.addBook("Java Game", "Lee");

        service.borrowBook("Maxim", "OCA: Oracle Certified Associate Java SE 8");
        service.borrowBook("Paul", "1984");
        service.borrowBook("Simon", "1984"); // Not available yet

        service.printBorrowedBooks("Maxim");
        service.returnBook("Maxim", "OCA: Oracle Certified Associate Java SE 8");
        service.printBorrowedBooks("Maxim");

        service.borrowBook("Simon", "1984");
        service.printBorrowedBooks("Simon");
    }
}