public class Pilha{
   
   String[] elementos;
   int topo;
   int tamanho;
   
   public Pilha(int tamanho){
      this.tamanho = tamanho;
      topo = -1;
      elementos= new String[tamanho];
   }
   
   public void push(String e){
      topo++;
      elementos[topo] = e;
   
   }
   
   public String pop(){
      String e;
      e=elementos[topo];
      topo--;
      return e;
   
   }
   
   public boolean isEmpty(){
      return(topo == -1);
      
   }
   
   public boolean isFull(){
      return (topo == tamanho);
   
   }
   
   public String top(){
      return elementos[topo];
   
   } 
   
   
   
   
   
}