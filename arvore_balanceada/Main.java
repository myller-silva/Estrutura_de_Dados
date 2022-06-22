package arvore_balanceada;

public class Main {
  public static void main(String[] args) {
    Arvore a = new Arvore();

    // a.insert(25, 50, 75, 100, 125, 150, 175, 200, 250, 300);
    
    a.insert( 30, 20, 15);

    System.out.println(a);

    System.out.println( a.getNode(15).pai );
    System.out.println( a.root.pai );
  }
}
