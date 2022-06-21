package arvore_balanceada;

public class Main {
  public static void main(String[] args) {
    Arvore a = new Arvore();
    a.insert(10, 1, 12, 5, 23, 2, 8, 13, 9);
    // a.insert(25, 50, 75, 100, 125, 150, 175);
    
    System.out.println(a);

  }
}
