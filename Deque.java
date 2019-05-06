package com.company;


import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private DoubleNode first;//assign first and last node for Deque
    private DoubleNode last;
    private int size;//keep track of size


    private class DoubleNode { //define DoubleNode for Deque
        Item item;
        DoubleNode next;
        DoubleNode prev;
    }

    public boolean isEmpty() {//check if Deque is empty
        return first == null;
    }

    public int size() {
        return size; //obtain the size of the list
    }

    public void Fenqueue(Item x) {//add to the front of the deque
        DoubleNode oldfirst = first;
        first = new DoubleNode();
        first.item = x;
        first.prev = null;
        if (size == 0) {
            last = first;
            first.next = null;
        } else {
            oldfirst.prev = first;
            first.next = oldfirst;
        }
        size++;
    }

    public Item FDequeue() {//remove from the front of the Deque
        if (isEmpty()) {
            return null;
        }
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }
        size--;
        return item;
    }

    public void Benqueue(Item x) {//add to the back of the deque
        DoubleNode oldlast = last;
        last = new DoubleNode();
        last.item = x;
        last.next = null;
        if (isEmpty()) {
            first = last;
            first.prev = null;
        } else {
            oldlast.next = last;
            last.prev = oldlast;
        }
        size++;
    }

    public Item BDequeue() {//remove from the back of the Deque
        if (isEmpty()) {
            return null;
        }
        Item item = last.item;
        last = last.prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
        return item;
    }

    public void before(Item item, DoubleNode N) {//insert before a given node
        if (N.prev == null) {
            Fenqueue(item);
        } else {
            DoubleNode newnode = new DoubleNode();
            newnode.item = item;
            newnode.next = N;
            newnode.prev = N.prev;
            N.prev.next = newnode;
            N.prev = newnode;
        }
        size++;
    }

    public void after(Item item, DoubleNode N) {//insert after a given node
        if (N.next == null) {
            Benqueue(item);
        } else {
            DoubleNode newnode = new DoubleNode();
            newnode.item = item;
            newnode.prev = N;
            newnode.next = N.next;
            N.next.prev = newnode;
            N.next = newnode;
        }
        size++;
    }

    public Item delete(DoubleNode N) {//delete a given node
        if (N.prev != null && N.next == null) {
            N.prev.next = null;
        }

        if (N.prev == null && N.next != null) {
            N.next.prev = null;
        }

        if (N.prev != null && N.next != null) {
            N.prev.next = N.next;
            N.next.prev = N.prev;
        }
        size--;
        return N.item;
    }

    public void FMove(DoubleNode N) {//move a node to the front
        Fenqueue(N.item);
        delete(N);
    }

    public void BMove(DoubleNode N) {//move a node to the back
        Benqueue(N.item);
        delete(N);
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }//implement iterator

    private class DequeIterator implements Iterator<Item> {
        private DoubleNode current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {

        Deque<Integer> DQ = new Deque<Integer>();
        DQ.Fenqueue(15);
        DQ.Benqueue(78);
        System.out.println(DQ.first.item);
        System.out.println(DQ.last.item);
        DQ.before(45,DQ.last);
        System.out.println(DQ.first.next.item);
        DQ.after(57,DQ.first.next);
        System.out.println(DQ.last.prev.item);
        DQ.FMove(DQ.last);
        System.out.println(DQ.first.item);
        DQ.BMove(DQ.first.next);
        System.out.println(DQ.last.item);
        DQ.FDequeue();
        System.out.println(DQ.first.item);
        DQ.BDequeue();
        System.out.println(DQ.size());
        DQ.before(45,DQ.last);
        DQ.after(57,DQ.first.next);
        DQ.after(60,DQ.first.next);
        System.out.println(DQ.size());
        DQ.FDequeue();
        System.out.println(DQ.size());
        DQ.BDequeue();
        System.out.println(DQ.size());
        DQ.delete(DQ.first.next);
        System.out.println(DQ.size());
    }
}
//OUTPUT /Library/Java/JavaVirtualMachines/jdk-10.0.1.jdk/Contents/Home/bin/java "-javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=58445:/Applications/IntelliJ IDEA CE.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath /Users/rohandatta/IdeaProjects/Question1/out/production/Question1 com.company.Deque
//15
//78
//45
//57
//78
//15
//45
//2
//5
//4
//3
//2
//
//Process finished with exit code 0



