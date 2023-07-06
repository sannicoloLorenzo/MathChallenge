
import java.util.Scanner;
import java.util.function.DoubleBinaryOperator;
import java.util.Random;
public class MathChallenge
{
		final static String ANSI_RESET = "\u001B[0m";
        final static String ANSI_RED = "\u001B[31m";
        final static String ANSI_GREEN = "\u001B[32m";
        final static String ANSI_CYAN = "\u001B[36m";
        final static String ANSI_PURPLE = "\u001B[35m";
		static int vite;
		static int score;
		static int num1;
		static int num2;
		static Random random=new Random();
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		
		int answ;
		vite=3;
		score=0;
		int ndom=1;
		int res;
		int level;
		System.out.println(ANSI_CYAN+"SFIDA MATEMATICA!"+ANSI_RESET);
		while(vite!=0){
		    System.out.println("Domanda numero: "+ndom);
			if(score<10)
		    	level=2;
			else if(score<15)
				level=3;
			else
				level=4;
			int op=random.nextInt(level);
		    switch(op){
		        case 0:
					answ=generateNumber((a, b) -> a + b," + ",100);
					Thread sleepingThread = Thread.currentThread();
		            try{
						Thread.sleep(5000);
					}catch(InterruptedException e){
						//...
					}
					res=in.nextInt();
					sleepingThread.interrupt();
					checkAnswer(res, answ,1);
		            ndom++;
		            break;
		        case 1:
		            answ=generateNumber((a, b) -> a - b," - ",100);
		            res=in.nextInt();
		            checkAnswer(res, answ,1);
		            ndom++;
		            break;
				case 2:
					answ=generateNumber((a, b) -> a * b," x ",20);
		            res=in.nextInt();
					checkAnswer(res, answ,2);
		            ndom++;
					break;
				case 3:
					answ=generateNumber((a, b) -> a / b," / ",50);
		            res=in.nextInt();
					checkAnswer(res, answ,3);
		            ndom++;
					break;
		    }
			if(score%10==0){
				vite++;
				System.out.println(ANSI_CYAN+"TRAGUARDO RAGGIUGNTO!"+ANSI_RESET);
				System.out.println("Hai guadagnato una vita!");
				System.out.println(ANSI_PURPLE+"Vite rimanenti: "+vite+ANSI_RESET);
			}

			if(score==10){
				System.out.println(ANSI_CYAN+"NUOVO LIVELLO RAGGIUGNTO!"+ANSI_RESET);
				System.out.println("Moltiplicazioni sbloccate, difficolta aumentata...");
			}
			else if(score==15){
				System.out.println(ANSI_CYAN+"NUOVO LIVELLO RAGGIUGNTO!"+ANSI_RESET);
				System.out.println("Divisioni sbloccate, difficolta aumentata...");
			}
				
		}
		in.close();
	System.out.println(ANSI_RED+"Game Over"+ANSI_RESET);
	System.out.println("score: "+score);
	}

	public static void checkAnswer(int res,int answ,int point){
		if(res==answ){
		    System.out.println(ANSI_GREEN+"Risposta corretta!"+ANSI_RESET);
		    score+=point;
		}else{
		    System.out.println(ANSI_RED+"Risposta errata!"+ANSI_RESET);
		    System.out.println("Risposta giusta: "+(answ));
		    vite--;
		    System.out.println(ANSI_PURPLE+"Vite rimanenti: "+vite+ANSI_RESET);
		}
	}

	public static int generateNumber(DoubleBinaryOperator operator ,String op, int genN){
		int answ;
		boolean correct=true;
		do{
			num1=1+random.nextInt(genN);
			num2=1+random.nextInt(genN);
			if(isInteger(operator.applyAsDouble(num1, num2))){
				correct=false;
			}
		}while(correct);
		System.out.print(num1+op+num2+" = ");
		answ=(int)operator.applyAsDouble(num1,num2);
		return answ;
	}

	public static boolean isInteger(double number){
        return number % 1 == 0;
    }
}
