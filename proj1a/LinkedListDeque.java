public class LinkedListDeque <jyp>{
   private static class IntNode<jyp>{
       public jyp item;
       public IntNode prev;
       public IntNode next;

       public IntNode(IntNode p,jyp j,IntNode n){
           item = j;
           prev = p;
           next = n;
       }
   }

   private IntNode sentinel;
   private int size;
   /* empty linked list*/
   public LinkedListDeque(){
     sentinel = new IntNode(null,null,null);
     size = 0;
     sentinel.prev = sentinel;    // make empty list's sentinel.next and sentinel.prev refers to sentinel itself
     sentinel.next = sentinel;
   }
    /* return a new copy of other */
    public LinkedListDeque(LinkedListDeque other){

        LinkedListDeque<jyp> refer = new LinkedListDeque<>();
        for(int i =0;i <= other.size - 1;i++){
            refer.addLast((jyp)other.get(i));
        }
        this.sentinel = refer.sentinel;
        this.size = refer.size;
    }

   /* not empty */
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }else{
            return false;
        }

    }
    /* return and delete the first item in list */
    public jyp removeFirst(){
        if(this.isEmpty()){
            return null;
        }else{
        jyp Return = (jyp)sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return Return;
    }}

    public void addFirst(jyp item){
        sentinel.next = new IntNode(sentinel,item,sentinel.next);
        sentinel.next.next .prev = sentinel.next;
        size += 1;
    }

    /* return the size */
    public int size(){
        return size;
    }

    /* return the ith item */
    public jyp get(int index){
        if(this.isEmpty()){
            return null;
        }else{
        IntNode iter = sentinel.next;
        while(index > 0){
            index  -= 1;
            iter = iter.next;
        }
        return (jyp)iter.item;
    }}

    public jyp helperget(IntNode node,int i){
        if(i == 0){
            return (jyp)node.item;
        }else{
            return helperget(node.next,i - 1);
        }
    }
    public jyp getRecursive(int index){      // method is used for Linkedlistdeque instance, not Intnode
        return this.helperget(this.sentinel.next,index);
    }

    /* add item to the last of the list */
    public void addLast(jyp item){
        sentinel.prev.next = new IntNode(sentinel.prev,item,sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    /* remove the last item */
    public jyp removeLast(){
        if(this.isEmpty()){
            return null;
        }else{
        jyp Return = (jyp) sentinel.prev.item;
        size -= 1;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return Return;
    }}

    /* print the list */
    public void printDeque(){
      for(int i = 0;i<= size -1;i++){
        System.out.print(this.get(i)+ " ");
    }
        System.out.println("");
    }



   public static void main(String[] args) {
       LinkedListDeque<Integer> l = new LinkedListDeque<>();
       l.printDeque();
       System.out.println(l.removeFirst());
       l.addFirst(1);
       l.addFirst(511);
       l.addLast(1314);
       System.out.println(l.get(0));
       System.out.println(l.get(1));
       System.out.println(l.get(2));
       System.out.println(l.size());
       l.printDeque();
       System.out.println(l.getRecursive(2));
//       l.printDeque();
//       System.out.println(l.removeFirst());
//       l.removeLast();
//       System.out.println(l.size());
//       System.out.println(l.get(0));
//       l.printDeque();
       LinkedListDeque<Integer> l2 = new LinkedListDeque<>(l);
       l2.printDeque();
       l2.removeFirst();
       l2.printDeque();
       l.printDeque();
   }
}
