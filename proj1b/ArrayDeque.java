

public class ArrayDeque<jyp> implements Deque<jyp>{
    private int prev;
    private int next;
    private jyp[] item;
    private int size;
   public ArrayDeque(){
       /* use circular form*/
       prev = 3;
       next = 4;
       item = (jyp[]) new Object[8];
       size = 0;
   }
   public ArrayDeque(ArrayDeque other){
       ArrayDeque<jyp> new_array = new ArrayDeque<>();

       for(int i = 0;i<= other.size-1;i++){
           new_array.addLast((jyp)other.get(i));
       }
       item = new_array.item;
       prev = new_array.prev;
       next = new_array.next;
       size = new_array.size;
   }
    public void resizing(int capacity){
       /* copy the list to a new list in the right order*
       then reset the prev and next
        */
       jyp[] item_new = (jyp[]) new Object[2 * capacity];
       System.arraycopy(item,prev+1,item_new,capacity/2,item.length-prev-1);
       System.arraycopy(item,0,item_new,capacity/2+item.length-prev-1,prev);

       item = item_new;
       prev = capacity/2 -1;
       next = prev + capacity;
    }
    /* add the given Item in front of (array)item */
    @Override
   public void addFirst(jyp Item){
       if(prev == next){
           this.resizing(size+1);
       }
       size += 1;
       item[prev] = Item;
       prev -= 1;
       if(prev < 0){
           prev = item.length-1;
       }
   }
    @Override
   public void addLast(jyp Item){
       if(prev == next){
           this.resizing(size+1);
       }
       size += 1;
       item[next] = Item;
       next += 1;
       if(next >= item.length)
           next = 0;
   }

    /* get the ith item */
    @Override
    public jyp get(int index){
       if(prev + index+1 >= item.length){
           return item[index - item.length + prev + 1 ];
       }
       return item[prev + index+1];
    }

    /* print the whole list */
    @Override
    public void printDeque(){
        if(this.isEmpty()){
            return;
        }
        for(int i = 0;i<=size-1 ;i++){
            System.out.print(this.get(i) + " ");
        }
        System.out.println("");
    }

    /* whether the list is empty */
//    public boolean isEmpty(){
//        if(size ==0){
//            return true;
//        }else{
//            return false;
//        }
//    }
    @Override
    public int size(){
        return this.size;
    }

    /*remove and return the first element */
    @Override
    public jyp removeFirst(){

        if(this.isEmpty()){
            return null;
        }
        else{
            size -= 1;
            if(prev == item.length-1){
                prev = -1;
            }
            prev = prev +1;
            jyp Return  = item[prev];
            item[prev] = null;
            return Return;
        }
    }
    /* similar to removeFirst */
    @Override
    public jyp removeLast(){
        if(this.isEmpty()){
            return null;
        }
        else{
            size -= 1;
            if(next ==0){
                next = item.length ;
            }
            next = next -1;
            jyp Return = item[next];
            item[next] = null;
            return Return;
        }
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<Integer>();
        test.addFirst(4);
        test.addFirst(3);
        test.addLast(2);
        test.addLast(1);
        test.addLast(5);
        test.addLast(6);
        test.addLast(7);
        /* test.item is [7,null,3,4,2,1,5,6]*/
        /* the question is that: */
        System.out.println(test.item[4]);  /* fail ClassCastException: object can not cast to Integer */
        System.out.println(((Object[])(test.item))[4]); /* this one succeed */


//        test.printDeque();
//////        System.out.println(test.get(4));    // successfully print 5
//        boolean result = test.item[4] instanceof Integer;  // this one fail  : class (object) can not be cast to integer     ?????????
//        System.out.println((Object [])(test.item)[4]);  // success! the important thing is that if class conflicts, you should convert it to the top class
//
////        test.resizing(test.size + 1);
//        System.out.println(test.prev+" " + test.next);
//        for(int i = 0;i<=7;i++){
//            test.addLast(i+10);
//        }
//        test.printDeque();
//////        test.resizing(test.size + 1);
////        int a = test.removeFirst();
////        int b = test.removeLast();
//        ArrayDeque<Integer> new_arry = new ArrayDeque<>(test);
//        new_arry.printDeque();
//        new_arry.removeLast();
//        test.printDeque();
//        new_arry.printDeque();
    }
}
