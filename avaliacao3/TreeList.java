package avaliacao3;

public class TreeList {
  TreeNode root;

  public boolean isEmpty() {
    return root == null;
  }

  public TreeNode getNode(int value) {
    return getNode(this.root, value);
  }

  private TreeNode getNode(TreeNode node, int value) {
    if (node == null) {
      return null;
    }
    if (value < node.value) {
      return getNode(node.left, value);
    } else if (node.value < value) {
      return getNode(node.right, value);
    } else {
      return node;
    }
  }

  public TreeNode insert(int item) {
    return insert(this.root, item);
  }

  private TreeNode insert(TreeNode ptr, int item) {
    if (ptr == null) {
      ptr = new TreeNode(item);
      ptr.left = null;
      ptr.right = null;
      if (root == null) {
        this.root = ptr;
      }
      return ptr;
    } else {
      TreeNode aux = null;
      if (item < ptr.value) {
        if (ptr.left == null) {
          ptr.left = insert(ptr.left, item);
        } else {
          aux = insert(ptr.left, item);
        }
        return (aux == null) ? ptr.left : aux;
      } else if (item > ptr.value) {
        if (ptr.right == null) {
          ptr.right = insert(ptr.right, item);
        } else {
          aux = insert(ptr.right, item);
        }
        return (aux == null) ? ptr.right : aux;
      } else {
        return null;
      }
    }
  }

  public String inOrder() {
    return inOrder(this.root);
  }

  public String inOrder(TreeNode node) {
    String str = "";
    if (node != null) {
      if (node.left != null) {
        str += inOrder(node.left);
      }
      str += node.value + " ";
      if (node.right != null) {
        str += inOrder(node.right);
      }
    }
    return str;
  }

  public String preOrder() {
    return preOrder(this.root);
  }

  public String preOrder(TreeNode node) {
    String str = "";
    if (node != null) {
      str += node.value + " ";
      if (node.left != null) {
        str += inOrder(node.left);
      }
      if (node.right != null) {
        str += inOrder(node.right);
      }
    }
    return str;
  }

  public String posOrder() {
    return posOrder(this.root);
  }

  public String posOrder(TreeNode node) {
    String str = "";
    if (node != null) {
      if (node.left != null) {
        str += inOrder(node.left);
      }
      if (node.right != null) {
        str += inOrder(node.right);
      }
      str += node.value + " ";
    }
    return str;
  }

  public TreeNode remove(int item) {
    if (isEmpty()) {
      return null;
    }
    if (item == this.root.value) {
      return this.removeRoot();
    }

    TreeNode father = getFather(item);
    TreeNode del = getSon(father, item);
    
    if(father == null || del == null){
      return null;
    }
    
    switch (del.numberOfChildren()) {
      case 0:
        deleteChildlessNode(father, del);
        break;
      case 1:
        deleteNodeWithOneChildren(father, del);
        break;
      case 2:
        deleteNodeWithTwoChildren(father, del);
        break;
      default:
        break;
    }
    return del;
  }

  public TreeNode getSon(TreeNode father, int item) {
    if(father==null){
      return null;
    }
    if(father.left!=null){
      if(father.left.value==item){
        return father.left;
      }
    }
    if(father.right!=null){
      if(father.right.value==item){
        return father.right;
      }
    }
    return null;
  }

  public TreeNode getFather(int value){
    return getFather(this.root, value);
  }

  private TreeNode getFather(TreeNode node, int value) { //ok
    while (node != null) {
      if (node.value < value) {
        if(node.right==null){ //funcao??
          return null;
        }else if (node.right.value == value){
          return node;
        }else{
          node = node.right;
        }
      } else if (node.value > value) { 
        if(node.left==null){ //funcao??
          return null;
        }else if (node.left.value == value){
          return node;
        }else{
          node = node.left;
        }
      } else {
        return null;
      }
    }
    return node;
  }

  private TreeNode removeRoot() {
    TreeNode temp = this.root;
    switch (this.root.numberOfChildren()) {
      case 0:
        this.root = null;
        break;
      case 1:
        this.root = (this.root.left != null) ? this.root.left : this.root.right;
        break;
      case 2:
        TreeNode aux = this.root.left;
        TreeNode aux2 = aux;
        while (aux2.right != null) {
          aux2 = aux2.right;
        }
        aux2.right = this.root.right;
        this.root = aux;
        break;
      default:
        break;
    }
    return temp;
  }

  private void deleteChildlessNode(TreeNode father, TreeNode del) {
    if (del.equals(father.left)) {
      father.left = null;
    } else {
      father.right = null;
    }
  }

  private void deleteNodeWithOneChildren(TreeNode father, TreeNode del) {
    TreeNode son = (del.left == null) ? del.right : del.left;
    if (son.value < father.value) {
      father.left = son;
    } else {
      father.right = son;
    }
  }

  private void deleteNodeWithTwoChildren(TreeNode father, TreeNode del) {
    TreeNode aux = del;
    if (del.equals(father.right)) {
      father.right = del.left;
    } else {
      father.left = del.left;
    }
    TreeNode aux2 = del.left;
    while (aux2.right != null) {
      aux2 = aux2.right;
    }
    aux2.right = aux.right;
  }

  @Override
  public String toString() {
    return this.strNodes(this.root);
  }

  private String strNodes(TreeNode node) {
    String str = "";
    if (node != null) {
      str += node + "\n";
      if (node.left != null) {
        str += strNodes(node.left);
      }
      if (node.right != null) {
        str += strNodes(node.right);
      }
    }
    return str;
  }

	public TreeNode insertAfter(){
		return null;
	}
	
}