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
      // System.out.println(this+"\n");
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
    while (node != null) {
      int esq = (node.esq == null) ? -1 : node.esq.alt;
      int dir = (node.dir == null) ? -1 : node.dir.alt;
      if (esq > dir + 1) {
        girarParaDireita(node);
      } else if (dir > esq + 1) {
        girarParaEsquerda(node);
      }
      node = node.pai;
    }
  }

  private void girarParaDireita(Node node) {

  }

  private void girarParaEsquerda(Node node) { // obs
    if (node.equals(this.root)) {
      giraRootEsquerda(node);
      return;
    } 
    
    Node nodeDir = node.dir;

    Node pai = node.pai;
    pai.dir = node.dir;
    node.dir.pai = pai; //
    Node nodeDirEsq = node.dir.esq;
    node.dir.esq = node;
    node.dir = nodeDirEsq;
    
    node.pai = nodeDir;
    if(nodeDirEsq!=null){
      nodeDirEsq.pai = node;
    }
    atualizarAltura(node);
  }
  
  private void giraRootEsquerda(Node node) { // obs: atualizar pai
    Node nodeDir = node.dir;
    node.dir = nodeDir.esq;
    nodeDir.esq = node;
    this.root = nodeDir;

    this.root.pai = null;
    node.pai = this.root;

    atualizarAltura(node);
  }

  private void atualizarAltura(Node node) {
    while (node != null) {
      node.alt = max(node.esq, node.dir) + 1;
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
