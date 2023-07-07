
import java.util.Scanner;
import java.util.function.DoubleBinaryOperator;
import java.util.InputMismatchException;
import java.util.Random;
public class MathChallenge
{
		final static String ANSI_RESET = "\u001B[0m";
        final static String ANSI_RED = "\u001B[31m";
        final static String ANSI_GREEN = "\u001B[32m";
        final static String ANSI_CYAN = "\u001B[36m";
        final static String ANSI_PURPLE = "\u001B[35m";
		final static int checkNull = -999999999;
		static int vite;
		static double score;
		static int num1;
		static int num2;
		static int countmolt=0;
		static double moltipilicatore=1;
		static int ndom=1;
		static Scanner in=new Scanner(System.in);
		static Random random=new Random();
		static boolean practiceMode=false;
	public static void main(String[] args) {

		int answ;
		int res;
		int level=2;
		int newVita=10;
		boolean nextLv1=false;
		boolean nextLv2=false;
		int mTime;
		vite=0;
		score=0;

		System.out.println("\n"+ANSI_CYAN+"SFIDA MATEMATICA!"+ANSI_RESET);
		int scelta;
		boolean sc=false;
		while(!sc){
			System.out.println("Inserire:\n - 1 per avviare il gioco;\n - 2 per entrare nella modalità di pratica (niente tempo e vite infinite);\n - 3 per terminare il programma");
			try{
				scelta=in.nextInt();
				switch(scelta){
					case 1:
						mTime=25000;
						vite=3;
						sc=true;
						System.out.println("Hai a disposizione "+(mTime/1000)+" secondi per risopndere ad ogni domanda!");
						System.out.println("L'inserimento di testo come risposta verrà considerata come rispostà errata, comportando la perdità di una vita ed i vantaggi acquisiti!\n");
						sleep(5000);
						break;
					case 2:
						practiceMode=true;
						level=4;
						sc=true;
						System.out.println(ANSI_CYAN+"MODALITà DI PRATICA"+ANSI_RESET);
						System.out.println("Inserire in qualsiasi momento del testo (es. a) come risposta per uscire dal gioco!\n");
						sleep(5000);
						break;
					case 3:
						sc=true;
						System.out.println("Uscita in corso...\n");
						break;
					default:
						System.out.println(ANSI_RED+"Input non valido"+ANSI_RESET);
						break;
				}
			}catch(InputMismatchException e){
				System.out.println(ANSI_RED+"Input non valido"+ANSI_RESET);
				in.nextLine();
			}
		}
	
		while(vite!=0 || practiceMode){
		    System.out.println("Domanda numero: "+ndom);
			int op=random.nextInt(level);
		    switch(op){
		        case 0:
					answ=generateNumber((a, b) -> a + b," + ",100);
					try{
						res=in.nextInt();
						checkAnswer(res, answ,1);
					}catch(InputMismatchException e){
						stringAnswer();
					}
		            break;
		        case 1:
		            answ=generateNumber((a, b) -> a - b," - ",100);
					try{
						res=in.nextInt();
						checkAnswer(res, answ,1);
					}catch(InputMismatchException e){
						stringAnswer();
					}
		            break;
				case 2:
					answ=generateNumber((a, b) -> a * b," x ",20);
					try{
						res=in.nextInt();
						checkAnswer(res, answ,2);
					}catch(InputMismatchException e){
						stringAnswer();
					}
					break;
				case 3:
					answ=generateNumber((a, b) -> a / b," / ",50);
					try{
						res=in.nextInt();
						checkAnswer(res, answ,3);
					}catch(InputMismatchException e){
						stringAnswer();
					}
					break;
		    }
			if(score>=newVita && !practiceMode){
				vite++;
				System.out.println(ANSI_CYAN+"TRAGUARDO RAGGIUGNTO!"+ANSI_RESET);
				System.out.println("punteggio: "+score);
				System.out.println("Hai guadagnato una vita!");
				System.out.println(ANSI_PURPLE+"Vite rimanenti: "+vite+ANSI_RESET);
				newVita+=10;
				sleep(5000);
			}

			if(score>10 && !nextLv1 && !practiceMode){
				System.out.println(ANSI_CYAN+"NUOVO LIVELLO RAGGIUGNTO!"+ANSI_RESET);
				System.out.println("Moltiplicazioni sbloccate, difficolta aumentata...");
				mTime=20000;
				level=3;
				System.out.println("Ora hai ha disposizione solo "+(mTime/1000)+" secondi per ogni domanda!");
				nextLv1=true;
				sleep(5000);
			}
			else if(score>20 && !nextLv2 && practiceMode){
				System.out.println(ANSI_CYAN+"NUOVO LIVELLO RAGGIUGNTO!"+ANSI_RESET);
				System.out.println("Divisioni sbloccate, difficolta aumentata...");
				mTime=15000;
				level=4;
				System.out.println("Ora hai ha disposizione solo "+(mTime/1000)+" secondi per ogni domanda!");
				nextLv2=true;
				sleep(5000);
			}
				System.out.println();
		}
		in.close();
	System.out.println(ANSI_RED+"Game Over"+ANSI_RESET);
	System.out.println("punteggio: "+score);
	}

	public static void checkAnswer(int res,int answ,int point){
		if(res==answ){
		    System.out.println(ANSI_GREEN+"Risposta corretta!"+ANSI_RESET);
			if(!practiceMode){
				fireSerie();
				score+=point*moltipilicatore;
				countmolt++;
			}

		}else{
		    System.out.println(ANSI_RED+"Risposta errata!"+ANSI_RESET);
		    System.out.println("Risposta giusta: "+(answ));
			if(!practiceMode){
				vite--;
		    	System.out.println(ANSI_PURPLE+"Vite rimanenti: "+vite+ANSI_RESET);
				if(moltipilicatore!=1){
					System.out.println(ANSI_RED+"Moltiplicatore serie di punti perduto!"+ANSI_RESET);
					moltipilicatore=1;
				}	
			countmolt=0;
			}

		}
		ndom++;
		sleep(3000);
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

	public static void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e){}
	}

	public static void fireSerie(){
		if(countmolt>10 && moltipilicatore!=2){
			moltipilicatore=2;
			System.out.println(ANSI_CYAN+"SERIE DI "+countmolt+" RISPOSTE CORRETTE!"+ANSI_RESET);
			System.out.println("I punti ottenuti verranno moltiplicati x"+moltipilicatore+" !");
		}else if(countmolt>6 && moltipilicatore!=1.5){
			moltipilicatore=1.5;
			System.out.println(ANSI_CYAN+"SERIE DI "+countmolt+" RISPOSTE CORRETTE!"+ANSI_RESET);
			System.out.println("I punti ottenuti verranno moltiplicati x"+moltipilicatore+" !");
		}else if(countmolt>2 && moltipilicatore!=1.25){
			moltipilicatore=1.25;
			System.out.println(ANSI_CYAN+"SERIE DI "+countmolt+" RISPOSTE CORRETTE!"+ANSI_RESET);
			System.out.println("I punti ottenuti verranno moltiplicati x"+moltipilicatore+" !");
		}
	}

	public static void stringAnswer(){
		
		if(!practiceMode){
			System.out.println(ANSI_RED+"Non è possibile inserire del testo nella risposta..."+ANSI_RESET);
			System.out.println("Quest'errore to consterà una vita, stai più attento!");
			vite--;
			System.out.println(ANSI_PURPLE+"Vite rimanenti: "+vite+ANSI_RESET);
			if(moltipilicatore!=1){
				System.out.println(ANSI_RED+"Moltiplicatore serie di punti perduto!"+ANSI_RESET);
				moltipilicatore=1;
			}	
			countmolt=0;
			ndom++;
			in.nextLine();
			sleep(3000);
		}else{
			System.out.println("Uscita in corso...");
			practiceMode=false;
		}
	}
}
