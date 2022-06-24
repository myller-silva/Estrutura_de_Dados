package avaliacao4;

import java.util.Random;

public class Main {
  public static void main(String[] args) {
    // Arvore arvore = new Arvore(100, 50, 150, 75, 125, 127, 80, 53, 77, 79);

    Arvore arvore = new Arvore();
    Random random = new Random();

    int tam = 50;
    for (int i = 0; i < tam; i++) {
      int n = random.nextInt(0, tam);
      arvore.insert(n);
    }
    System.out.println("Arvore:");
    System.out.println(arvore);
    System.out.println("root: "+arvore.root);
  }

}
