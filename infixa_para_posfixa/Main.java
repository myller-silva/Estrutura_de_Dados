package infixa_para_posfixa;

public class Main {
  public static void main(String[] args) {
		String str = "";
		java.util.Scanner scan = new java.util.Scanner(System.in);
		
		String esc="";
		do{
			System.out.print("Digite a expressao: ");
			str = scan.nextLine();
			System.out.println("\nExpressao: "+str);
			String posfixa = Converter.toPosfix(str) ;
			System.out.println("Posfixa: "+ posfixa);		
			System.out.println("Calculo: "+Calcular.expressaoPosfixa(posfixa));
			do{
				System.out.print("\nContinuar? [S/N] ");
				esc = scan.nextLine(); 
			}while(!esc.equals("N") && !esc.equals("S") && !esc.equals("n") && !esc.equals("s") );
		}while(!esc.equals("N") && !esc.equals("n"));
    
		scan.close();
  } 
}

// (25+432.0)*(1337+42)+9.0  ok
// (25+432.0)*(1337+42)+9.0 + 1.0.0 ok
// 25+432.0)*(1337+42+9.0  ok
// 2 + 8 + ok
// ( 2 + .3) ok
// 2(12+12) ok 
// 2(-12+12) 

