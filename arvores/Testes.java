package arvores;

import java.util.Scanner;

// clear && javac arvores/Testes.java && java Testes

public class Testes {
  public static void main(String[] args) {
    TreeList tree = new TreeList();

    // System.out.println(tree);
    Scanner scan = new Scanner(System.in);
    int n = 0 ;
    do{
      System.out.print("value: ");
      n = scan.nextInt();
      scan.nextLine();
			tree.insert(n);			
			System.out.println(tree);      
    }while(true);
  }
}
