import java.util.Scanner;
import java.util.HashMap;

// user class to store user details
class User{
    private String username;
    private String password;
    
    public User(String username, String password){
        this.username=username;
        this.password=password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}

// reservation class to store reservation details
class Reservation{
private static int pnrCounter = 1001;
private int pnr;
private String trainNumber;
private String trainName;
private String classType;
private String dateOfJourney;
private String from;
private String to;

public Reservation(String trainNumber, String trainName, String classType, String dateOfJourney, String from, String to){
    this.pnr=pnrCounter++;
    this.trainNumber=trainNumber;
    this.trainName=trainName;
    this.classType=classType;
    this.dateOfJourney=dateOfJourney;
    this.from=from;
    this.to=to;
}

public int getPnr(){
    return pnr;
}

  public void displayDetails(){
    System.out.println("PNR"+ pnr);
    System.out.println("Train number"+ trainNumber);
    System.out.println("Train name"+ trainName);
    System.out.println("Class type"+ classType);
    System.out.println("Date of journey"+ dateOfJourney);
    System.out.println("From"+from +"To"+to);
  }
}



public class OnlineReservationSystem{
private static HashMap<String, User> users = new HashMap<>();
private static HashMap<Integer, Reservation> reservations =new HashMap<>();

public static void main(String[] args) {
    Scanner scanner=new Scanner(System.in);
    
    // Intialize with a default user
    users.put("user1",new User("user1","password1"));
    System.out.println("Login");
    System.out.println("Enter username");
    String username=scanner.next();
    System.out.println("Enter password");
    String password =scanner.next();

    if(validateLogin(username, password)){
        System.out.println("Login successful");
        while(true){
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Exit");
            System.out.println("Enter your choise");
            int choise = scanner.nextInt();
            switch(choise){
                case 1:
                makeReservation(scanner);
                break;
                case 2:
                cancelReservation(scanner);
                break;
                case 3:
                System.out.println("Exiting");
                System.exit(0);
                break;
                default:
                System.out.println("Invalid choise. Try again");         
            }
        }
    } else{
        System.out.println(" Invalid login credentials.");
    }
    scanner.close();
}

private static boolean validateLogin(String username, String password ){
    User user=users.get(username);
    return user!= null && user.getPassword().equals(password);
}

private static void makeReservation(Scanner scanner){
    System.out.println("Enter Train number");
    String trainNumber=scanner.next();
    System.out.println(" Enter train name");
    String trainName=scanner.next();
    System.out.println(" Enter class typr");
    String classType=scanner.next();
    System.out.println(" Enter date of your journey (YYYY-MM-DD)");
    String dateOfJourney = scanner.next();
    System.out.println(" Enter from(place)");
    String from = scanner.next();
    System.out.println("Enter to (destination)");
    String to = scanner.next();

    Reservation reservation = new Reservation(trainNumber,trainName,classType,dateOfJourney,from,to);
    reservations.put(reservation.getPnr(),reservation);
    System.out.println(" reservation successful! your pnr is"+ reservation.getPnr());
}
private static void cancelReservation(Scanner scanner){
    System.out.println("Enter pnr number");
    int pnr = scanner.nextInt();
    Reservation reservation = reservations.get(pnr);
    if(reservation != null){
        System.out.println(" Reservation details");
        reservation.displayDetails();
        System.out.println(" do yopu want to cancel the reservation ? ( yes/no)");
        String confirmation = scanner.next();
        if(confirmation.equalsIgnoreCase("yes")){
            reservations.remove(pnr);
            System.out.println(" Reservation canceled successfully");
        }else{
            System.out.println(" cancel aborted");
        }

   }else {
    System.out.println(" PNR not found");
   }
}
}
