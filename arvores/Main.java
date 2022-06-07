package arvores;

public class Main {
	public static void main(String[] args) {
		Vetor v = new Vetor(0, 1, 2, 3, 4, 5, 6, 7, 8);
		TreeList arvore = constroiArvore(v);
		System.out.println("arvore:");
		System.out.println(arvore);
	}

	public static TreeList constroiArvore(Vetor v) {
		if (v == null || v.values.length == 0) {
			return null;
		}
		TreeList tree = new TreeList();
		tree.root = subArvore(v, 0, v.values.length);
		return tree;
	}

	public static TreeNode subArvore(Vetor v, int inf, int sup) {
		int media = (inf + sup) / 2;
		int value = v.values[media];
		TreeNode root = new TreeNode(value);

		if (inf != media) {
			root.left = subArvore(v, inf, media);
		}

		if (media + 1 - sup != 0) {
			root.right = subArvore(v, media + 1, sup);
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