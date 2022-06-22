package arvore_balanceada;

public class Arvore {
  Node root;

  public boolean isEmpty() {
    return (this.root == null);
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
    Node aux = node.pai;
    if (aux != null) {
      if (aux.esq != null && aux.dir != null) {
        if(aux.esq.alt > aux.dir.alt+1){
          girarParaDireita(aux);
        }else if(aux.dir.alt > aux.esq.alt+1){
          girarParaEsquerda(aux);
        }
      }      
      balancear(node.pai);
    }
  }
  private void girarParaDireita(Node node) {
    
  }
  private void girarParaEsquerda(Node node) {
    if(node.equals(this.root)){
      rotacionarRootParaEsquerda();
      return;
    }
    if(node.pai==null){
      return;
    }
    Node pai = node.pai;
    Node dir = node.dir;
    Node aux = dir.esq;
    pai.dir = dir;
    dir.esq = node;
    node.dir = aux;
    atualizarAltura(node);
  }

  private void rotacionarRootParaEsquerda() {
    Node aux = this.root.dir;
    Node auxEsq = aux.esq;
    this.root.dir = auxEsq;
    aux.esq = this.root;
    this.root = aux;
    atualizarAltura(root);
  }

  private void atualizarAltura(Node node) {
    Node aux = node;
    while (aux != null) {
      Node max = max(aux.esq, aux.dir);
      aux.alt = (max == null) ? 0 : max.alt + 1;
      aux = aux.pai;
    }
  }

  private Node max(Node n1, Node n2) {
    if (n1 == null) {
      return n2;
    }
    if (n2 == null) {
      return n1;
    }
    if (n1.alt < n2.alt) {
      return n2;
    } else {
      return n1;
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
