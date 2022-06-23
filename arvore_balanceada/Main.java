package arvore_balanceada;

public class Main {
  public static void main(String[] args) {
    Arvore a = new Arvore();

    // a.insert(25, 50, 75, 100, 125, 150, 175, 200, 250, 300);
    // int[] v = { 4, 2, 3, 1, 5, 7, 9, 6, 8, 11, 10 };
    // int v[] = {4, 3, 2, 1, 0, -4, -1, -3, -2, -5, -6, -7};
    int v[] = {1, 3, 2, 4, 5, 6, 9, 8, 7, 10, 11, 12};
    a.insert(v);
    System.out.println(a);
    valor_e_pai(a, v);
  }



  public static void valor_e_pai(Arvore a, int v[]) {
    for (int i : v) {
      Node e = a.getNode(i);
      System.out.println("valor: " + e.valor+" , pai: " + ( e.pai != null ? e.pai.valor : null ) );
    }
    System.out.println();
  }
}
