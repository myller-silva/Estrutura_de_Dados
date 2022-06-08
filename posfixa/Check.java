package posfixa;

public class Check {

  public static boolean isInfix(String str) {
    int tam = str.length();
    int aux = 0;
    // verificar parenteses:
    for (int i = 0; i < tam; i++) {
      char c = str.charAt(i);
      if (c == '(') {
        aux++;
      } else if (c == ')') {
        aux--;
      }
    }
    if (aux != 0) {
      return false;
    }
    // verificar operacoes:
    for (int i = 0; i < tam - 1; i++) {
      char c = str.charAt(i);
      if (c == ' ')
        continue;
      char prox = str.charAt(i + 1);
      if (isOper(c) && isOper(prox)) {
        return false;
      } else if (!isOper(c) && !Character.isDigit(c) && c != '(' && c != ')' && c != '.') {
        return false;
      }
    }

    return true;
  }

  public static boolean isInfix(String[] str) {
    if (str.length < 3)
      return false;

    if (!parentesesOk(str)) {
      return false;
    }
    if (!operadoresOk(str)) {
      return false;
    }
    if (haLetras(str)) {
      return false;
    }

    return true;
  }

  public static boolean isInfix(Fila expressao) {
    if (expressao == null)
      return false;
    if (expressao.length < 3)
      return false;
    if (!parentesesOk(expressao)) {
      return false;
    }
    if (!operadoresOk(expressao)) {
      return false;
    }
    if (haLetras(expressao)) {
      return false;
    }

    return true;
  }

  public static boolean operadoresOk(String[] str) {
    String ant = "";
    String s = "";
    String prox = "";

    if (isOper(str[0]))
      return false;

    for (int i = 0; i < str.length - 2; i++) {
      ant = str[i];
      s = str[i + 1];
      prox = str[i + 2];

      if (isOper(s)) {
        if (isOper(ant) || isOper(prox))
          return false;
        if (isParenteses(ant) || isParenteses(prox)) {
          if (ant.equals("(") || prox.equals(")")) {
            return false;
          }
        }
      }
    }

    if (isOper(prox))
      return false;

    return true;
  }

  public static boolean operadoresOk(Fila expressao) {
    if (expressao == null)
      return false;
    String ant = "";
    String s = "";
    String prox = "";

    // if(isOper(expressao.primeiro.value)) return false;

    Node i = expressao.primeiro;
    while (i.next.next != null) {
      ant = i.value;
      s = i.next.value;
      prox = i.next.next.value;

      if (isOper(s)) {
        if (isOper(ant) || isOper(prox))
          return false;
        if (isParenteses(ant) || isParenteses(prox)) {
          if (Check.isDouble(prox)) { // obs
            i = i.next;
            continue;
          }
          if (ant.equals("(") || prox.equals(")")) {
            return false;
          }
        }
      }

      i = i.next;
    }

    if (isOper(prox))
      return false;

    return true;
  }

  public static boolean isParenteses(String str) {
    switch (str) {
      case "(":
      case ")":
        return true;
      default:
        return false;
    }
  }

  public static boolean isParenteses(char c) {
    switch (c) {
      case '(':
      case ')':
        return true;
      default:
        return false;
    }
  }

  public static boolean isOper(char c) {
    switch (c) {
      case '+':
      case '-':
      case '*':
      case '/':
        return true;
      default:
        return false;
    }
  }

  public static boolean isOper(String c) {
    switch (c) {
      case "+":
      case "-":
      case "*":
      case "/":
        return true;
      default:
        return false;
    }
  }

  public static boolean isDouble(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean isInt(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean parentesesOk(String[] expressao) {
    int aux = 0;
    for (String str : expressao) {
      if (str.equals("(")) {
        aux++;
      } else if (str.equals(")")) {
        aux--;
      }
      if (aux < 0)
        return false;
    }
    return (aux == 0) ? true : false;
  }

  public static boolean parentesesOk(Fila expressao) {
    if (expressao == null)
      return false;
    int aux = 0;
    Node p = expressao.primeiro;

    while (p != null) {
      if (p.value.equals("(")) {
        aux++;
      } else if (p.value.equals(")")) {
        aux--;
      }
      if (aux < 0)
        return false;
      p = p.next;
    }

    return (aux == 0) ? true : false;
  }

  public static boolean haLetras(String[] expressao) {
    for (String str : expressao) {
      switch (str) {
        case "":
        case "+":
        case "-":
        case "*":
        case "/":
        case "(":
        case ")":
          break;
        default:
          if (!Check.isDouble(str))
            return true;
      }
    }
    return false;
  }

  public static boolean haLetras(Fila expressao) {
    Node no = expressao.primeiro;
    while (no != null) {
      switch (no.value) {
        case "":
        case "+":
        case "-":
        case "*":
        case "/":
        case "(":
        case ")":
          break;
        default:
          if (!Check.isDouble(no.value))
            return true;
      }
      no = no.next;
    }
    return false;
  }

  public static boolean isPosfix(String str) {
    if (str == null)
      return false;

    Fila lista = Converter.paraFila_posfixa(str);
    // System.out.println(lista);

    if (lista == null)
      return false;
    if (lista.length < 3)
      return false;
    if (isOper(lista.primeiro.value))
      return false;
    if (isOper(lista.primeiro.next.value))
      return false;
    if (!posfixaEmOrdem(lista))
      return false;

    return true;
  }

  public static boolean posfixaEmOrdem(Fila lista) {
    if (lista == null)
      return false;
    if (lista.isEmpty())
      return false;

    int aux = 0;
    Node p = lista.primeiro;

    while (p != null) {
      if (Check.isOper(p.value)) {
        aux--;
      } else if (Check.isDouble(p.value)) {
        aux++;
      } else {
        return false;
      }
      if (aux <= 0)
        return false;
      p = p.next;
    }
    return (aux == 1 ? true : false);
  }

  public static boolean posfixaEmOrdem(String expressao) {
    Fila filaExp = Converter.paraFila_posfixa(expressao);
    return posfixaEmOrdem(filaExp);
  }

}
