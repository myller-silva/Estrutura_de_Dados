package avaliacao4;

public class Arvore {
  Node root;

  public boolean isEmpty() {
    return (this.root == null);
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
        // System.out.println("girarParaDireita");
        girarParaDireita(node);
      } else if (dir > esq + 1) {
        // System.out.println("girarParaEsquerda");
        girarParaEsquerda(node);
      }
      node = node.pai;
    }
  }








  private void ajustarGiroParaDireita(Node A){
    Node B = A.esq;
    Node C = B.dir;
    Node D = C.esq;
    A.esq = C;
    C.esq = B;
    B.dir = D;
    B.pai = C;
    C.pai = A;
    if(D!=null){
      D.pai = B;
    }
    atualizarAltura(B);
  }

  private void girarParaDireita(Node A) {
    
    // System.out.println(">>> antes: \n"+this+"\n");
    if (A.equals(this.root)) {
      girarRootDireita(A);
      return;
    }
    
    if(A.esq.esq==null){
      ajustarGiroParaDireita(A);
    }

    Node P = A.pai;
    Node B = A.esq;
    Node D  = B.dir;

    if(P.esq.equals(A)){
      P.esq = B;
    }else{
      P.dir = B;
    }
    
    B.dir = A;
    A.esq = D;

    A.pai = B;
    B.pai = P;
    if(D!=null){
      D.pai = A;
    }

    // System.out.println(">>> depois: \n"+this+"\n");
    atualizarAltura(A);
  }

  
  
  private void girarRootDireita(Node node) {
    Node aux = node.esq;
    Node auxDir = aux.dir;
    if (aux.esq == null) {
      ajustarGiroParaDireita(node);
    }
    this.root = aux;
    node.esq = aux.dir;
    aux.dir = node;

    this.root.pai = null;
    node.pai = aux;

    if (auxDir != null) {
      auxDir.pai = node;
    }
    atualizarAltura(node);
  }


  


  private void girarParaEsquerda(Node node) {
    if (node.equals(this.root)) {
      girarRootEsquerda(node);
      return;
    }
    
    if(node.dir.dir==null){
      ajustarGiroParaEsquerda(node);
    }

    Node pai = node.pai;
    Node nodeDir = node.dir;
    Node aux = node.dir.esq;

    if(pai.esq.equals(node)){
      pai.esq = nodeDir;
    }else{
      pai.dir = nodeDir;
    }

    node.dir = aux;
    nodeDir.esq = node;
    node.pai = nodeDir;
    nodeDir.pai = pai;
    if(aux!=null){
      aux.pai = node;
    }
    atualizarAltura(node);
  }

  private void girarRootEsquerda(Node node) {  
    if(node.dir.dir==null){
      ajustarGiroParaEsquerda(node);
    }
    Node root = this.root;
    Node rootDir = this.root.dir;
    Node rootDirEsq = root.dir.esq;
    root.dir = rootDirEsq;
    rootDir.esq = root;
    root.pai = rootDir;
    rootDir.pai = null;
    if(rootDirEsq!=null){
      rootDirEsq.pai = root;
    }
    this.root = rootDir;
    atualizarAltura(node);
  }

  private void ajustarGiroParaEsquerda(Node A) {
    Node B = A.dir, C = A.dir.esq, D = A.dir.esq.dir;
    A.dir = C;
    C.dir = B;
    B.esq = D;

    C.pai = A;
    B.pai = C;
    if(D!=null){
      D.pai = B;
    }
    atualizarAltura(B);
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
