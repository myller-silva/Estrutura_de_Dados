package big_int;

public class Node{
  String value;
  Node anterior;
  Node proximo;
  Node(){
    anterior = null;
    proximo = null;
    value = null;
  }
  Node(String value){
    this();
    this.value=value;
  }
  @Override
  public String toString() {
    return this.value;
  }
}