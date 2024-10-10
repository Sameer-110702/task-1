import java.util.Scanner;
import java.util.Random;

 public class NumberGuessingGame{
     public static void main(String [] args){
     Scanner scanner=new Scanner(System.in);
     Random random= new Random();
     System.out.println("enter the maximum rounds");
     int maxRounds = scanner.nextInt();
     int totalScore=0;
   
        for(int round=1; round<=maxRounds; round++){
          System.out.println(" round"+round+"of"+ maxRounds);
          int numberToGuess= random.nextInt(100)+1;
          System.out.println("enter the maximum attempts");
          int maxAttempts=scanner.nextInt();
          int attempts=0;
          boolean guessedCorrectly=false;

           while(attempts<maxAttempts && !guessedCorrectly){
             System.out.print(" enter your guess(1-100)");
             int userGuess=scanner.nextInt();
             attempts++;
               if (userGuess==numberToGuess) {
                 System.out.println("you have guessed the number in"+ attempts+"attempts");
                 guessedCorrectly=true;
                 totalScore +=calculateScore(attempts);
                }else if(userGuess<numberToGuess){
                 System.out.println("too low, try again");
                }else{
                System.out.println(" too high, try again ");
                }
            }
            if(!guessedCorrectly){
                System.out.println(" sorry, you have used all"+" "+ maxAttempts+" " + "attempts.the right number was"+" "+ numberToGuess);
            }
            System.out.println("your score after this round"+" "+ totalScore);
        }  
        System.out.println("game over, you have score"+" "+ totalScore);
    }

    public static int calculateScore(int attempts){
        if(attempts==1) return 10;
        else if(attempts <= 3) return 5;
        else if(attempts <= 5) return 2;
        else if(attempts <= 10) return 1;
        else return 0;

    }
}