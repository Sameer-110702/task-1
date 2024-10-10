import java.util.Scanner;
public class OnlineExame {
   
    private String userName;
    private String password;
    private boolean isLoggedIn;
    private int timeRemaining;
    private int questionCount;
    private int[] userAnswers;
    private int[] correctAnswer;

    public OnlineExame(String userName,String password){
        this.userName=userName;
        this.password=password;
        System.out.println(" your registration is successfull !");
        this.isLoggedIn=false;
        this.timeRemaining=10; // in minutes
        this.questionCount=10;
        this.userAnswers= new int[questionCount];
        this.correctAnswer=new int[questionCount];

        for(int i=0; i<questionCount; i++){
            correctAnswer[i]=(int) Math.round(Math.random());
        }
    }

    public void login(){
        System.out.println(" login here to attend the examination");
        Scanner sc =new Scanner( System.in);
        System.out.print(" username");
        String InputUsername=sc.nextLine();
        System.out.print(" passwor ");
        String InputPassword=sc.nextLine();
        if(InputUsername.equals(userName) && InputPassword.equals(password)){
            isLoggedIn=true;
            System.out.println(" you have logedin into your account, all thr best for your examination");
        }else{
            System.out.println(" invalid credentials, please try again!");
        }

    }
    
    public void logout(){
        isLoggedIn=false;
        System.out.println(" logout successfull");
    }

    public void startExam(){
     if(!isLoggedIn){
        System.out.println(" it seems you are new user! please login");
        return;
     }  
     Scanner sc=new Scanner(System.in);
     System.out.println(" hurry up! you have"+" "+timeRemaining+" "+"minutes to compleate the exam" );
     for(int i=0; i<questionCount; i++){
        System.out.println("question"+(i+1)+":");
        System.out.println("1.option 1");
        System.out.println("2.option 2");
        System.out.println(" choose your answer(1 or 2):");
        int answer=sc.nextInt();
        userAnswers[i]=answer;
     }
     System.out.println("do you wanna submit the answer? \n 1:yes \n 2:no ");
     int n=sc.nextInt();
    if( n==1){
        submitExam();
    }else{
       try {
        Thread.sleep(timeRemaining*10*1000);   
       }catch(InterruptedException e){
         e.printStackTrace();
         submitExam();
       }
    }
}
public void submitExam(){
    if(!isLoggedIn){
        System.out.println(" it seems are a new user! please login");
        return;
    }
    int score=0;
    for(int i=0; i<questionCount;i++){
        if(userAnswers[i]==correctAnswer[i]){
            score++;
        }
    }
System.out.println("you have scored"+" "+ score +" "+ " out of"+" "+ questionCount+".");
System.out.println("congratulations");
logout();
}
public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    System.out.println(" enter your credentials like username and password");
    String uName=sc.nextLine();
    String pWord=sc.nextLine();
    OnlineExame onlineExame=new OnlineExame(uName, pWord);
    onlineExame.login();
    onlineExame.startExam();
}
}

