package avaliacao2;

public class MM_Math {
  
  public static String multiplicar(String str1, String str2) {
    ListaDupla lista1 = fatiar(str1), lista2 = fatiar(str2);
    Node calda1 = lista1.fim, calda2 = lista2.fim;
    ListaDupla resultado = gerarListaDupla(calda1, calda2);
    int buffer=0, n1=0, n2=0, res=0;
    Node aux = resultado.fim;
    while (calda1!=null) {
      calda2 = lista2.fim;
      n1 = Integer.parseInt(calda1.value);
      Node aux2 = aux;
      while (calda2!=null) {
        n2 = Integer.parseInt(calda2.value);
        buffer = Integer.parseInt(aux2.value);
        res = n1*n2+buffer;
        buffer = res/100;
        res -= buffer*100;
        aux2.value = String.format("%02d", res); //(res<=9)? "0"+res : ""+res ;
        buffer += Integer.parseInt(aux2.anterior.value);
        aux2.anterior.value = String.format("%02d", buffer); //(buffer<=9) ? "0"+buffer: ""+buffer;
        aux2 = aux2.anterior;
        calda2 = calda2.anterior;
      }
      aux=aux.anterior;
      calda1=calda1.anterior;
    }
    return formatar(resultado.str()) ;
  }  
  
  private static String formatar(String str){
    int length = str.length(), i = 0;
    for (i = 0; i < length; i++) {
      if(str.charAt(i)!='0') break;
    }
    return str.substring(i, length);
  }

  private static ListaDupla gerarListaDupla(Node calda1, Node calda2) {
    ListaDupla lista = new ListaDupla();
    while (calda1!=null) {
      lista.addStart("0");
      while (calda2!=null) {
        lista.addStart("0");
        calda2 = calda2.anterior;
      }
      calda1 = calda1.anterior;
    }
    return lista;
  }

  private static ListaDupla fatiar(String str) {
    ListaDupla lista = new ListaDupla();
    int length = str.length(), inicio = 0;
    if(length%2==1) lista.addEnd( str.charAt(inicio++) );
    for(int i = inicio; i < length; i+=2) lista.addEnd( str.substring(i, i+2) );
    return lista;
  }

  // private static String notacaoCientifica(String num) {
  //   String str = num.charAt(0)+"." ;
  //   int length = num.length();
  //   int tam = (length<8)? length : 8;
  //   for (int i = 1; i < tam; i++) {
  //     str+=num.charAt(i);
  //   }
  //   str+=" *e^+"+(length-1);
  //   return str;
  // }
  
  public static String multiplicar2(String str1, String str2) {
    ListaDupla lista1 = fatiar(str1), lista2 = fatiar(str2), resultado = new ListaDupla();
    Node calda1 = lista1.fim, calda2 = lista2.fim;
    int buffer=0, n1=0, n2=0, res=0;
    Node aux = null;
    Node aux2 = null;

    while (calda1!=null) {
      calda2 = lista2.fim;
      n1 = Integer.parseInt(calda1.value);
      aux2 = aux;
      while (calda2!=null) {
        n2 = Integer.parseInt(calda2.value);
        res = n1*n2;
        if(aux2!=null){
          res += Integer.parseInt(aux2.value);
          buffer = res/100 ;
          res -= buffer*100;
          aux2.value = (res<10) ? "0"+res : ""+res;
          if(aux2.anterior==null){
            if(calda2.anterior!=null){
              resultado.addStart(buffer);
            }else{
              if (buffer!=0) resultado.addStart(buffer);
            }
          }else{
            aux2.anterior.value = ""+(buffer+Integer.parseInt(aux2.anterior.value));
          }
        }else if( resultado.inicio!=null ){ //
          res += Integer.parseInt(resultado.inicio.value);
          buffer = res/100;
          res -= buffer*100;
          resultado.inicio.value = (res<10) ? "0"+res : ""+res;
          resultado.addStart(buffer);
        }else{
          buffer = res/100;
          res -= buffer*100;
          resultado.addStart((res<10) ? "0"+res : ""+res);
          resultado.addStart((buffer<10) ? "0"+buffer : ""+buffer);
        }
        if(aux2!=null) aux2 = aux2.anterior;
        calda2 = calda2.anterior;
      }
      aux = ( aux==null  ) ? resultado.fim.anterior : aux.anterior;
      calda1=calda1.anterior;
    }
    return resultado.toString();
    // return resultado.str();
  }
}
