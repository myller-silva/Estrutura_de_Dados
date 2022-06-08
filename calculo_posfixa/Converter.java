package calculo_posfixa;

public class Converter {

  public static String toPosfix(String exp){
    Pilha opStack = new Pilha();
    Fila postFixList = new Fila();
    Fila tokenList = paraFila_infixa(exp);

    if(!Check.isInfix(tokenList)){
      // return "nao é infixa";
      return null;
    }
    
    Node token = tokenList.primeiro; 
    while (token!=null) {
      if(token.value==""){
        token=token.next;
      }
      if( Check.isDouble(token.value) ) {
        postFixList.push(token.value);
      }else if( token.value.equals("(") ){
        opStack.push(token.value);
      }else if( token.value.equals(")") ){
        String topToken = opStack.primeiro.value;
        opStack.pop();
        // obs:
        while( !topToken.equals("(") ) {
          postFixList.push(topToken);
          topToken = opStack.primeiro.value;
          opStack.pop();
        }
      }else{
        while( !opStack.isEmpty() && (precedencia(opStack.primeiro.value) >= precedencia(token.value)) ){
          postFixList.push(opStack.primeiro.value);
          opStack.pop();
        }
        opStack.push(token.value);
      }
      token=token.next;
    }
    while(! opStack.isEmpty() ){
      postFixList.push(opStack.primeiro.value);
      opStack.pop();
    }
    String str="";
    Node item = postFixList.primeiro;
    while(item!=null){
      str += (item.value + " ");
      item = item.next;
    }
    return str;
  }

  public static Fila paraFila_posfixa(String str) {
    if(str==null) return null;
    Fila fila = new Fila();
		int tam = str.length();
    String num = "";
    for (int i = 0; i < tam; i++) {
      char c = str.charAt(i);
      do{
        num+=c;
        try{
          c = str.charAt(++i);
        }catch( StringIndexOutOfBoundsException e){
          break;
        }
      }while(c!=' ' );
      fila.push(num);
      num="";
      
      // if ( Check.isOper(c) ) {
      //   fila.push(c);
      //   continue;
      // }
      // do{
      //   num+=c;
      //   try{
      //     c = str.charAt(++i);
      //   }catch( StringIndexOutOfBoundsException e){
      //     break;
      //   }
      // }while(c!=' ' );
      // if(Check.isDouble(num)) {
      //   fila.push(num);
      // }
      // num="";
      // if(Check.isOper(c)) {
      //   fila.push(c);
      //   continue;
      // }
    }
    // System.out.println("str[tam-2]: " + str.charAt(tam-2));
    return fila;
  }

	public static Fila paraFila_infixa(String expressao) {
    if(expressao==null) return null;
    
    Fila fila = new Fila();
		
    String token = "";
		int tam = expressao.length();
		
    for (int i = 0; i < tam; i++) {
      char c = expressao.charAt(i);
      if(Character.isDigit(c)) {
        do{
          token+=c;
          try{
            c=expressao.charAt(++i);
          }catch(StringIndexOutOfBoundsException e ){
            break;
          }
        }while (Character.isDigit(c) || c=='.');
        fila.push(token);
        token="";
        i--; continue;
      }
			if(c==' ') continue;
      
      if (c=='-') {
        String ant="";
        try{
          int aux=1;
          while (ant.equals("")) {
            char temp = expressao.charAt(i-aux);
            if(temp==' ') aux++;
            else ant += temp;
          }
        }catch(StringIndexOutOfBoundsException e){}
        if(!Check.isDouble(ant) && !Check.isOper(ant)){
          do{
            token+=c;
            try{
              c=expressao.charAt(++i);
            }catch(StringIndexOutOfBoundsException e ){
              break;
            }
          }while (Character.isDigit(c) || c=='.');
          fila.push(token);
          token="";
          i--; continue;
        }
      }
      fila.push(c); 
    }

    if(!token.equals("")){
      fila.push(token);
    }
    
    return expressaoMatematica(fila);
  }
  
  public static Fila expressaoMatematica(Fila fila) {
    if (fila==null) return null;
    Fila novaFila = new Fila();
    Node p = fila.primeiro;
    while (p!=null) {
      novaFila.push(p.value);
      boolean isDouble = Check.isDouble(p.value) ;
        if( isDouble || p.value.equals(")")){
          try{
            if(p.next.value.equals("(")) novaFila.push("*");
          }catch(java.lang.NullPointerException e){}
        }
      p=p.next;
    }
    return novaFila;
  }
	
  public static String expressaoMatematica(String str) {
    return paraFila_infixa(str).toString();
  }
  
  private static int precedencia(String op) { 
    switch (op) {
      case "*":
      case "/":
        return 3;
      case "+":
      case "-":
        return 2;
      case "(":
        return 1;
    
      default:
        return -1;
    }
  }

}
