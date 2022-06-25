package avaliacao4;

import java.util.Random;

public class Main {
  public static void main(String[] args) {
    ArvoreAVL arvore = new ArvoreAVL();
    Random random = new Random();
    int qtd = 50, lim = 50;
    for (int i = 0; i < qtd; i++) {
      int n = random.nextInt(0, lim);
      arvore.insert(n);
    }
    System.out.println("Arvore: ");
    System.out.println(arvore);
  }
}
