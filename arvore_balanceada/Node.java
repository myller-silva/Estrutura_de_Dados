package arvore_balanceada;

public class Node {
  Node pai;
  Node esq;
  Node dir;
  int alt;
  int valor;
  
  Node(int valor){
    this.valor=valor;
  }

  
  @Override
  public String toString() {
    String esq = String.format("%4s", (this.esq == null) ? null: this.esq.valor+"");
    String dir = String.format("%4s", (this.dir == null) ? null: this.dir.valor+"");
    String valor = String.format("%2d", this.valor);
    return esq + " <- " + valor + " -> " + dir;
  }
}
