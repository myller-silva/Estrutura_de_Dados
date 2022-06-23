package avaliacao4;

public class Node {
  Node pai, esq, dir;
  int alt, valor;

  Node(int valor) {
    this.valor = valor;
    this.alt = 0;
  }

  @Override
  public String toString() {
    String esq = String.format("%4s", (this.esq == null) ? null : this.esq.valor + "");
    String dir = String.format("%4s", (this.dir == null) ? null : this.dir.valor + "");
    String valor = String.format("%2d", this.valor);
    return esq + " <- " + valor + " -> " + dir+" , "+this.alt;
    // return esq + " <- " + valor + " -> " + dir;
  }
}
