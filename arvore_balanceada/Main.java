package arvore_balanceada;

public class Main {
  public static void main(String[] args) {
    Arvore a = new Arvore();

    a.insert(25, 50, 75, 100, 125, 150, 175);
    System.out.println("Arvore: ");
    System.out.println(a);
  }
}
