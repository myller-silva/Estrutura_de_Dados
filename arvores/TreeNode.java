package arvores;

public class TreeNode {
  TreeNode left;
  TreeNode right;
  // TreeNode father;
  int value;
  
  TreeNode(int value) {
    this.value = value;
  }
  
  private boolean childless() {
    return (this.left == null && this.right == null);
  }

  private boolean twoChildren() {
    return (this.left != null && this.right != null);
  }
  
  public int numberOfChildren() {
    if (this.childless()) {
      return 0;
    }
    if(twoChildren()){
      return 2;
    }
    return 1;
  }
  
  @Override
  public String toString() {
    String left = String.format("%4s", (this.left == null) ? null: this.left.value+"");
    String right = String.format("%4s", (this.right == null) ? null: this.right.value+"");
    String value = String.format("%2d", this.value);
    return left + " <- " + value + " -> " + right;
  }
}
