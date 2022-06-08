package arvore;

public class Main {
  public static void main(String[] args) {
    Vector v = new Vector(25, 50, 75, 100, 125, 150, 175);
    TreeList tree = makeTree(v);
    System.out.println("Vetor: " + v);
    System.out.println("Arvore: ");
    System.out.print(tree);
  }

  public static TreeList makeTree(Vector v) {
    if (v == null || v.values.length == 0) {
      return null;
    }
    TreeList tree = new TreeList();
    tree.root = subTree(v, 0, v.values.length);
    return tree;
  }

  public static TreeNode subTree(Vector v, int inf, int sup) {
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

class Vector {
  Integer[] values;

  Vector(Integer... values) {
    this.values = values;
    this.sort();
  }

  public void sort() {
    int len = values.length;
    int temp;
    for (int i = len - 1; i > 0; i--) {
      for (int j = 0; j < i; j++) {
        if (values[j] > values[j + 1]) {
          temp = values[j];
          values[j] = values[j + 1];
          values[j + 1] = temp;
        }
      }
    }
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