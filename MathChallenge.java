
import java.util.Scanner;
import java.util.Random;
public class Main
{
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		Random random=new Random();
		final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_PURPLE = "\u001B[35m";
		int vite=3;
		int score=0;
		int ndom=1;
		int res;
		int num1;
		int num2;
		System.out.println(ANSI_CYAN+"SFIDA MATEMATICA!"+ANSI_RESET);
		while(vite!=0){
		    System.out.println("Domanda numero: "+ndom);
		    int op=random.nextInt(2);
		    switch(op){
		        case 0:
		            num1=1+random.nextInt(100);
		            num2=1+random.nextInt(100);
		            System.out.print(num1+" + "+num2+" = ");
		            res=in.nextInt();
		            if(res==num1+num2){
		                System.out.println(ANSI_GREEN+"Risposta corretta!"+ANSI_RESET);
		                score++;
		            }else{
		                System.out.println(ANSI_RED+"Risposta errata!"+ANSI_RESET);
		                System.out.println("Risposta giusta: "+(num1+num2));
		                vite--;
		                System.out.println(ANSI_PURPLE+"Vite rimanenti: "+vite+ANSI_RESET);
		            }
		            ndom++;
		            break;
		        case 1:
		            num1=1+random.nextInt(100);
		            num2=1+random.nextInt(100);
		            System.out.print(num1+" - "+num2+" = ");
		            res=in.nextInt();
		            if(res==num1-num2){
		                System.out.println(ANSI_GREEN+"Risposta corretta!"+ANSI_RESET);
		                score++;
		            }else{
		                System.out.println(ANSI_RED+"Risposta errata!"+ANSI_RESET);
		                System.out.println("Risposta giusta: "+(num1-num2));
		                vite--;
		                System.out.println(ANSI_PURPLE+"Vite rimanenti: "+vite+ANSI_RESET);
		            }
		            ndom++;
		            break;
		    }
		}
	System.out.println(ANSI_RED+"Game Over"+ANSI_RESET);
	System.out.println("score: "+score);
	}
}
