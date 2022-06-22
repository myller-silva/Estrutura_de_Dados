package arvore_balanceada;

public class Main {
  public static void main(String[] args) {
    Arvore a = new Arvore();
    // a.insert(10, 1, 12, 5, 23, 2, 8, 13, 9);
    // a.insert(25, 50, 75, 100, 125, 150, 175);
    // a.insert(8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15, 16, 17);
    a.insert(8, 4, 12, 2, 6, 10, 14, 16, 15, 17, 18,19);
    System.out.println(a);

  }
}
