
import java.util.Scanner;

class ATM {
    private static final int MAX_TRANSACTIONS =10;
    private static final int MAX_BALANCE=100000;
    private String userId;
    private int userPin;
    private double balance;
    private String[] transactionHistory= new String[MAX_TRANSACTIONS];
    private int transactionCount=0;
    public ATM(String userId,int userPin){
        this.userId=userId;
        this.userPin=userPin;
        this.balance=0;
     }
     public boolean authenticate(String userId,int userPin){
        return this.userId.equals(userId) && this.userPin==userPin;
    }
    public void traansactionHistory(){
        System.out.println("Transaction History");
        for(int i=transactionCount-1;i>=0;i--){
            System.out.println(transactionHistory[i]);
        }
    }
    public void withdraw(double amount){
        if(amount>balance || amount > MAX_BALANCE){
            System.out.println("insuficient funds or withdrawal limit exceeded");
        }else{
            balance-=amount;
            transactionCount=(transactionCount+1)%MAX_TRANSACTIONS;
            transactionHistory[transactionCount]="Withdraew"+amount;
            System.out.println("Withdrawal successful. Your new balance is"+balance);
        }
    }
    public void deposit(double amount){
        if (amount>MAX_BALANCE){
            System.out.println("Deposit limit exceeded");
        }else{
            balance+=amount;
            transactionCount=(transactionCount+1)%MAX_TRANSACTIONS;
            transactionHistory[transactionCount]="Deposited"+amount;
            System.out.println("deposited successful.Your new balance is"+ balance);
        }
    }
    public void transfer( double amount,ATM otherATM){
        if(amount>balance || amount> MAX_BALANCE){
             System.out.println("insuficient funds or transfer limit exceeded");
        }else{
            balance-=amount;
            otherATM.balance += amount;
            transactionCount=(transactionCount+1)%MAX_TRANSACTIONS;
            transactionHistory[transactionCount] ="Transferred"+amount+"to"+otherATM.userId;
            System.out.println("Transer successful.Your new balanceis"+balance);      
         }
    }
}
public class ATMinterface{
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter your user ID");
        String userId = scanner.nextLine();
        System.out.println("enter your pin");
        int userPin= scanner.nextInt();
        ATM atm = new ATM( userId,userPin);
        if(atm.authenticate(userId, userPin)){
            System.out.println("Authenticate successful. welcome");
            while(true){
                System.out.println("\nchoose an option");
                System.out.println("1. Transactions History");
                System.out.println("2. withdraw");
                System.out.println("3. Deposit");
                System.out.println("4.Transfer");
                System.out.println("5. Quit");

                int choise =scanner.nextInt();
                switch ( choise ) {
                    case 1:
                    atm.traansactionHistory();
                    break;
                    case 2:
                    System.out.println("Enter withdrawl amount");
                    double withdrawlAmount=scanner.nextDouble();
                    break;
                    case 3:
                    System.out.println("Enter deposit amount");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                    case 4:
                    System.out.println("recipient's user ID");
                    String recipientUserId=scanner.nextLine();

                    System.out.println("enter transfer amount");
                    double transferAmount =scanner.nextDouble();
                    ATM recipientATM=new ATM(recipientUserId,0);
                    atm.transfer(transferAmount, recipientATM);
                    break;
                    case 5:
                    System.out.println("Thank you for using the ATM!");
                    System.exit(0);
                    default:
                    System.out.println("invalid choise please try again");
                }
            }
        }else{
            System.out.println("Authentication failed.Please yrt again");
        }

        
    }
}
