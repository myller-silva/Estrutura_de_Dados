package arvore;

public class Main {
  public static void main(String[] args) {
    Vetor v = new Vetor(0, 1, 2, 3, 4, 5, 6, 7, 8);
    TreeList tree = makeTree(v);
    System.out.println("arvore: ");
    System.out.println(tree);
  }

  public static TreeList makeTree(Vetor v) {
    if (v == null || v.values.length == 0) {
      return null;
    }
    TreeList tree = new TreeList();
    tree.root = subTree(v, 0, v.values.length);
    return tree;
  }

  public static TreeNode subTree(Vetor v, int inf, int sup) {
    int media = (inf + sup) / 2;
    TreeNode root = new TreeNode(v.values[media]);
    if (inf != media) {
      root.left = subTree(v, inf, media);
    }
    if (media + 1 != sup) {
      root.right = subTree(v, media + 1, sup);
    }
    return root;
  }

}

class Vetor {
  int[] values;

  Vetor(int... values) {
    this.values = values;
  }

  @Override
  public String toString() {
    String str = "";
    for (int v : values) {
      str += v + " ";
    }
    return str;
  }
}