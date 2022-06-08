package posfixa;

public class Main {
	public static void main(String[] args) {
		String str = "";
		java.util.Scanner scan = new java.util.Scanner(System.in);

		String esc = "";
		do {
			System.out.print("Digite a expressao: ");
			str = scan.nextLine();
			System.out.println("\nExpressao: " + str);
			String posfixa = Converter.toPosfix(str);
			System.out.println("Posfixa: " + posfixa);
			System.out.println("Calculo: " + Calcular.expressaoPosfixa(posfixa));
			do {
				System.out.print("\nContinuar? [S/N] ");
				esc = scan.nextLine();
			} while (!esc.equals("N") && !esc.equals("S") && !esc.equals("n") && !esc.equals("s"));
		} while (!esc.equals("N") && !esc.equals("n"));

		scan.close();
	}
}
