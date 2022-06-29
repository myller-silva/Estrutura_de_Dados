package avaliacao4;

public class ArvoreAVL {
  Node root;
  int length=0;

  ArvoreAVL() {
  }

  ArvoreAVL(int... values) {
    this.insert(values);
  }

  public boolean isEmpty() {
    return (this.root == null);
  }

  public Node insert(int... items) {
    Node node = null;
    for (int item : items) {
      node = insert(item);
    }
    return node;
  }

  public Node insert(int item) {
    Node node = insert(this.root, item, null);
    atualizarAltura(node);
    balancear(node);
    return node;
  }

  private Node insert(Node ptr, int item, Node pai) {
    if (ptr == null) {
      ptr = new Node(item);
      ptr.esq = ptr.dir = null;
      ptr.pai = pai;
      if (root == null) {
        this.root = ptr;
      }
      this.length++; //
      return ptr;
    } else {
      Node aux = null;
      if (item < ptr.valor) {
        if (ptr.esq == null) {
          ptr.esq = insert(ptr.esq, item, ptr);
        } else {
          aux = insert(ptr.esq, item, ptr);
        }
        return (aux == null) ? ptr.esq : aux;
      } else if (item > ptr.valor) {
        if (ptr.dir == null) {
          ptr.dir = insert(ptr.dir, item, ptr);
        } else {
          aux = insert(ptr.dir, item, ptr);
        }
        return (aux == null) ? ptr.dir : aux;
      } else {
        return null;
      }
    }
  }

  private void balancear(Node node) {
    while (node != null) {
      int esq = (node.esq == null) ? -1 : node.esq.alt;
      int dir = (node.dir == null) ? -1 : node.dir.alt;
      if (esq > dir + 1) {
        node = girarParaDireita(node);
      } else if (dir > esq + 1) {
        node = girarParaEsquerda(node);
      }
      node = node.pai;
    }
  }

  private Node girarParaDireita(Node node) {
    if (node.esq.esq == null) {
      ajustarGiroParaDireita(node);
    }
    if (node.equals(this.root)) {
      return girarRootDireita(node);
    }
    Node pai = node.pai, aux = node.esq, aux2 = aux.dir;
    if (pai.esq.equals(node)) {
      pai.esq = aux;
    } else {
      pai.dir = aux;
    }
    aux.dir = node;
    node.esq = aux2;
    node.pai = aux;
    aux.pai = pai;
    if (aux2 != null) {
      aux2.pai = node;
    }
    atualizarAltura(node);
    return aux;
  }

  private void ajustarGiroParaDireita(Node node) {
    Node aux = node.esq, aux2 = aux.dir, aux3 = aux2.esq;
    node.esq = aux2;
    aux2.esq = aux;
    aux.dir = aux3;
    aux.pai = aux2;
    aux2.pai = node;
    if (aux3 != null) {
      aux3.pai = aux;
    }
    atualizarAltura(aux);
  }

  private Node girarRootDireita(Node node) {
    Node aux = node.esq, aux2 = aux.dir;
    this.root = aux;
    node.esq = aux.dir;
    aux.dir = node;
    this.root.pai = null;
    node.pai = aux;
    if (aux2 != null) {
      aux2.pai = node;
    }
    atualizarAltura(node);
    return this.root;
  }

  private Node girarParaEsquerda(Node node) {
    if (node.dir.dir == null) {
      ajustarGiroParaEsquerda(node);
    }
    if (node.equals(this.root)) {
      return girarRootEsquerda(node);
    }
    Node pai = node.pai,  aux = node.dir,  aux2 = node.dir.esq;
    if (pai.esq.equals(node)) {
      pai.esq = aux;
    } else {
      pai.dir = aux;
    }
    node.dir = aux2;
    aux.esq = node;
    node.pai = aux;
    aux.pai = pai;
    if (aux2 != null) {
      aux2.pai = node;
    }
    atualizarAltura(node);
    return aux;
  }

  private void ajustarGiroParaEsquerda(Node node) {
    Node aux = node.dir, aux2 = node.dir.esq, aux3 = node.dir.esq.dir;
    node.dir = aux2;
    aux2.dir = aux;
    aux.esq = aux3;
    aux2.pai = node;
    aux.pai = aux2;
    if (aux3 != null) {
      aux3.pai = aux;
    }
    atualizarAltura(aux);
  }

  private Node girarRootEsquerda(Node node) {
    Node aux = this.root, aux2 = this.root.dir, aux3 = aux.dir.esq;
    aux.dir = aux3;
    aux2.esq = aux;
    aux.pai = aux2;
    aux2.pai = null;
    if (aux3 != null) {
      aux3.pai = aux;
    }
    this.root = aux2;
    atualizarAltura(node);
    return this.root;
  }

  private void atualizarAltura(Node node) {
    while (node != null) {
      // int temp = node.alt;
      node.alt = max(node.esq, node.dir) + 1;
      // if(temp == node.alt){ //obs
      //   break;
      // }
      node = node.pai;
    }
  }

  private int max(Node n1, Node n2) {
    if (n1 != null && n2 != null) {
      return (n1.alt > n2.alt) ? n1.alt : n2.alt;
    }
    if (n1 != null) {
      return n1.alt;
    }
    if (n2 != null) {
      return n2.alt;
    }
    return -1;
  }

  public Node getNode(int valor) {
    return getNode(this.root, valor);
  }

  private Node getNode(Node node, int valor) {
    if (node == null) {
      return null;
    }
    if (valor < node.valor) {
      return getNode(node.esq, valor);
    } else if (node.valor < valor) {
      return getNode(node.dir, valor);
    } else {
      return node;
    }
  }

  @Override
  public String toString() {
    return this.strNodes(this.root);
  }

  private String strNodes(Node node) {
    String str = "";
    if (node != null) {
      str += node + "\n";
      if (node.esq != null) {
        str += strNodes(node.esq);
      }
      if (node.dir != null) {
        str += strNodes(node.dir);
      }
    }
    return str;
  }
}
