package avaliacao2;

import java.util.Scanner;

public class Main {
  static Scanner scan = new Scanner(System.in);
  public static void main(String[] args) {
    String str1="", str2="", resultado="", format="", esc="";
    int length=0;
    boolean isInteger = false;
    System.out.println(">>> Multiplicar numeros enormes <<<");
    do{
      do{
        System.out.print("1º numero: ");
        str1 = scan.nextLine();
        isInteger = isInteger(str1);
        if(!isInteger) System.out.println("entrada invalida");
      }while(!isInteger);
      do{
        System.out.print("2º numero: ");
        str2 = scan.nextLine();
        isInteger = isInteger(str2);
        if(!isInteger) System.out.println("entrada invalida");
      }while(!isInteger);

      resultado = MM_Math.multiplicar(str1, str2);
      length = resultado.length();
      format = "%"+(length+5)+"s\n";

      System.out.printf("\n"+format, str1 );
      System.out.printf(format, str2 );
      System.out.printf(format, ("x"+"_".repeat(length)) );
      System.out.printf(format+"\n", resultado );
      // System.out.printf(format+"\n", length );
      do{
        System.out.print("Continuar? [S/N] ");
        esc = scan.nextLine();
      }while(
        !esc.equals("S") &&
        !esc.equals("s") &&
        !esc.equals("N") &&
        !esc.equals("n")
      );
      System.out.println();
    } while ( !esc.equals("N") && !esc.equals("n") );
  }

  public static boolean isInteger(String str) {
    int length = str.length();
    for (int i = 0; i < length; i++) {
      if (!Character.isDigit( str.charAt(i) )) return false;
    }
    return true;
  }  
}
