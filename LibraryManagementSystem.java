import java.io.*;
import java.util.*;

class book{
    int slNo;
    String bookName;
    String authorName;
    int bookQtyCopy;
    int bookQty;

    public book(int slNo, String bookName, String authorName, int qty){
        this.slNo=slNo;
        this.bookName=bookName;
        this.authorName=authorName;
        this.bookQtyCopy=qty;
        this.bookQty=qty;
    }
}

public class LibraryManagementSystem{
    book theBooks[]= new book[50];
    public static int count;

        Scanner input= new Scanner(System.in);

        public int compareBookObjects(book b1, book b2){
            if(b1.bookName.equalsIgnoreCase(b2.bookName)){
                System.out.println("The name of this book is already exists");
                return 0;
            }
            if(b1.slNo==b2.slNo){
                System.out.println("The serial number of this book already exists");
                return 0;
            }
            return 1;
        }


    public void addBook(book b){
        for(int i = 0; i < count; i++){
            if(this.compareBookObjects(b, this.theBooks[i]) == 0)
                return;
        }
        if(count < 50){
            theBooks[count]=b;
            count++;
        } else{
            System.out.println("Slot filled!..No more space to add books.");
        }
    }
    
    public void searchBySlNo(){
        System.out.println("\t\t\t\tSearch a Book by its Serial Number\n");
        System.out.println("Enter Serial number of the book you required:");
        int slNo=input.nextInt();
        int flag=0;
        System.out.println("SlNo\t\tName\t\tAuthor\t\tAvailable Qty of Books\t\tTotal Qty of Books");
        for(int i=0;i<count;i++){
            if(slNo==theBooks[i].slNo){
                System.out.println(theBooks[i].slNo + "\t\t" +  theBooks[i].bookName  + "\t\t"  +  theBooks[i].authorName  +  "\t\t" +  theBooks[i].bookQtyCopy  +  "\t\t"  + theBooks[i].bookQty);
                flag++;
                return;
            }
        }
        if(flag==0)
           System.out.println("No books are available for the serial number" +slNo+ "entered");
    }

    public void searchByAuthorName(){
        System.out.println("\t\t\t\tSearch By Authors Name\n");
        input.nextLine();
        System.out.println("Enter the Author Name of the book you required:");
        String authorName=input.nextLine();
        int flag=0;
        System.out.println("SlNo\t\tName\t\tAuthor\t\tAvailable Qty of Books\t\tTotal Qty of Books");
        for(int i=0;i<count;i++){
            if(authorName.equalsIgnoreCase(theBooks[i].authorName)){
                System.out.println(theBooks[i].slNo + "\t\t" + theBooks[i].bookName + "\t\t" + theBooks[i].authorName + "\t\t" + theBooks[i].bookQtyCopy + "\t\t" + theBooks[i].bookQty);
                flag++;
            }    
        }
        if(flag==0)
           System.out.println("No books are available for the serial number" +authorName+ "entered"); 
    }

    public void showAllBooks(){
        System.out.println("\t\t\t\tShowing All  Books\n");
        System.out.println("SlNo\t\tName\t\tAuthor\t\tAvailable Qty of Books\t\tTotal Qty of Books");
        for(int i=0;i<count;i++){
            System.out.println(theBooks[i].slNo + "\t\t" + theBooks[i].bookName + "\t\t" + theBooks[i].authorName + "\t\t" + theBooks[i].bookQtyCopy + "\t\t" + theBooks[i].bookQty);
        }
    }

    public void upgradeBookQty(){
        System.out.println("\t\t\t\tUpgrade Quantity of the Books\n");
        System.out.println("Enter Serial number of the book:");
        int slNo=input.nextInt();
        for(int i=0;i<count;i++){
            if(slNo==theBooks[i].slNo){
                System.out.println("Enter no of books to be added:");
                int addingQty=input.nextInt();
                theBooks[i].bookQty+=addingQty;
                theBooks[i].bookQtyCopy+=addingQty;
                return;
            }    
        }
        System.out.println("Book not found.");
    }

    public void displayMenu(){

        System.out.println("");
        System.out.println("Press 1 to Add a new Book.");
        System.out.println("Press 0 to Exit from the Application.");
        System.out.println("Press 2 to Upgrade Quantity of the Book.");
        System.out.println("Press 3 to Search for  a new Book.");
        System.out.println("Press 4 to Show the list of Books.");
        System.out.println("Press 5 to Student Registration.");
        System.out.println("Press 6 to Show the Registered Students.");
        System.out.println("Press 7 to Check OUT Book.");
        System.out.println("Press 8 to Check IN Book.");
        System.out.println("");

    }

    public int isAvailable(int slNo){
        for(int i=0;i<count;i++){
            if(slNo==theBooks[i].slNo){
                if(theBooks[i].bookQtyCopy>0){
                    System.out.println("The Book you are Searching for is Available.");
                    return i;
                }
                System.out.println("The Book you are Searching for is UnAvailable.");
                return -1;
            }
        }
        System.out.println("The Serial Number you have entered for the Book is UnAvailable in the Library.");
        return -1;
    }

    public book CheckOutBook(){
        System.out.println("Enter Serial Number of the Book to be Checked Out.");
        int slNo=input.nextInt();
        int bookIndex=isAvailable(slNo);
        if(bookIndex!=-1){
            theBooks[bookIndex].bookQtyCopy--;
            return theBooks[bookIndex];
        }
        return null;
    }

    public void checkInBook(book b){
        for(int i=0;i<count;i++){
            if(b.slNo==theBooks[i].slNo){
                theBooks[i].bookQtyCopy++;
                return;
            }
        }
        System.out.println("Book Not Found!");
    }
    public static void main(String[] args){
        LibraryManagementSystem library=new LibraryManagementSystem();
        Scanner input=new Scanner(System.in);

        while(true){
            library.displayMenu();
            int choice=input.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Enter serial number,Book Name,Author Name,and Quantity:");
                    int slNo=input.nextInt();
                    input.nextLine();
                    String bookName=input.nextLine();
                    String authorName=input.nextLine();
                    int qty=input.nextInt();
                    library.addBook(new book(slNo,bookName,authorName,qty));
                    break;
                case 2:
                    library.upgradeBookQty();
                    break;
                case 3:
                    library.searchBySlNo();
                    break;
                case 4:
                    library.showAllBooks();
                    break;
                case 0:
                    System.out.println("Exiting the Application!....");
                    input.close();
                    return;
                default:
                    System.out.println("Invalid choice!Please Try Again.");                    
            }
        }
    }
}