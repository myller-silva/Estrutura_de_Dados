package arvore_balanceada;

public class Arvore {
  Node root;

  public boolean isEmpty() {
    return (this.root==null);
  }

  public Node getNode(int valor){
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
    Node aux = null;
    for (int item : items) {
      aux = insert(item);
    }
    return aux;
  }

  public Node insert(int item) {
    Node aux = insert(this.root, item, null);
    balancear(aux);
    return aux;
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

  private void balancear(Node aux) {
    
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

