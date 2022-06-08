package big_int;

public class ListaDupla {
  Node inicio;
  Node fim;

  public ListaDupla() {
    this.inicio = null;
    this.fim = null;
  }

  public boolean delete(String value) {
    if (isEmpty())
      return false;
    if (this.inicio.value.equals(value))
      return deleteStart();
    if (this.fim.value.equals(value))
      return deleteEnd();

    Node no = this.inicio.proximo;
    while (no.proximo != null) {
      if (no.value.equals(value)) {
        Node ant = no.anterior;
        Node prox = no.proximo;
        ant.proximo = prox;
        prox.anterior = ant;
        return true;
      }
      no = no.proximo;
    }
    return false;
  }

  public boolean deleteAllReferences(String value) {
    if (isEmpty())
      return false;
    boolean excluido = false;

    while (this.inicio.value.equals(value)) {
      if (deleteStart())
        excluido = true;
      if (isEmpty())
        return excluido;
    }
    while (this.fim.value.equals(value)) {
      if (deleteEnd())
        excluido = true;
      if (isEmpty())
        return excluido;
    }

    Node no = this.inicio;
    while (no != null) {
      if (no.value.equals(value)) {
        Node ant = no.anterior;
        Node prox = no.proximo;
        ant.proximo = prox;
        prox.anterior = ant;
        excluido = true;
      }
      no = no.proximo;
    }
    return excluido;
  }

  public boolean addBefore(String v1, String v2) {
    if (v2.equals(this.inicio.value))
      return addStart(v1);
    Node no = this.inicio;
    while (no != null) {
      if (no.value.equals(v2)) {
        Node novo = new Node(v1);
        novo.proximo = no;
        novo.anterior = no.anterior;
        no.anterior = novo;
        novo.anterior.proximo = novo;
        return true;
      }
      no = no.proximo;
    }
    return false;
  }

  public boolean addAfter(String v1, String v2) {
    Node no = this.inicio;
    while (no.proximo != null) {
      if (no.value.equals(v2)) {
        Node novo = new Node(v1);
        novo.proximo = no.proximo;
        novo.anterior = no;
        no.proximo.anterior = novo;
        no.proximo = novo;
        return true;
      }
      no = no.proximo;
    }
    if (v2.equals(this.fim.value))
      return addEnd(v1);
    return false;
  }

  public boolean addStart(int value) {
    return addStart(String.format("%d", value));
  }

  public boolean addStart(char value) {
    return addStart(String.format("%c", value));
  }

  public boolean addStart(double value) {
    return addStart(String.format("%.0f", value));
  }

  public boolean addStart(String value) {
    if (value == null)
      return false;
    Node no = new Node(value);
    if (isEmpty()) {
      no.anterior = null;
      no.proximo = null;
      this.inicio = no;
      this.fim = no;
      return true;
    }
    no.proximo = this.inicio;
    this.inicio.anterior = no;
    this.inicio = no;

    return true;
  }

  public boolean addEnd(char value) {
    return addEnd(String.format("%c", value));
  }

  public boolean addEnd(String value) {
    if (value == null)
      return false;
    Node no = new Node(value);
    if (isEmpty()) {
      no.anterior = null;
      no.proximo = null;
      this.inicio = no;
      this.fim = no;
      return true;
    }
    no.proximo = null;
    no.anterior = this.fim;
    this.fim.proximo = no;
    this.fim = no;
    return true;
  }

  public boolean deleteEnd() {
    if (isEmpty())
      return false;
    if (this.fim.equals(this.inicio)) {
      this.inicio = null;
      this.fim = null;
      return true;
    }
    this.fim = this.fim.anterior;
    this.fim.proximo = null;
    return true;
  }

  public boolean deleteStart() {
    if (isEmpty())
      return false;
    if (this.fim.equals(this.inicio)) {
      this.inicio = null;
      this.fim = null;
      return true;
    }
    this.inicio = this.inicio.proximo;
    this.inicio.anterior = null;
    return false;
  }

  public boolean isEmpty() {
    return (this.inicio == null && this.fim == null);
  }

  public String toString() {
    Node no = this.inicio;
    String str = "";
    while (no != null) {
      str += no.value + " ";
      no = no.proximo;
    }
    return str;
  }

  public String toString(boolean inverso) {
    if (!inverso)
      return this.toString();
    String str = "";
    Node no = this.fim;
    while (no != null) {
      str += no.value + " ";
      no = no.anterior;
    }
    return str;
  }

  public String str() {
    String str = "";
    Node no = this.inicio;
    while (no != null) {
      str += no.value;
      no = no.proximo;
    }
    return str;
  }

}
