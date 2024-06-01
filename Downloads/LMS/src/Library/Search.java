package Library;

import java.util.Scanner;

public class Search implements IOOperation {

    @Override
    public void oper(Database database, User user) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter book category: ");
        String category = s.nextLine();
        System.out.println("Enter book name: ");
        String name = s.nextLine();
        System.out.println();
        Book foundBook = database.searchBook(name, category);
        if (foundBook != null) {
            System.out.println("\n" + foundBook.toString() + "\n");
        } else {
            System.out.println("Book doesn't exist in the specified category!\n");
        }

        user.menu(database, user);
    }
}