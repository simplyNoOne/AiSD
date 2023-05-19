package lab2;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            this.object=e;
        }
        E object;
        Element next=null;
    }

    Element sentinel;

    int size;
    private class InnerIterator implements Iterator<E>{
        // TODO
        E next;
        int pos;
        public InnerIterator() {
            // TODO
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public E next() {
            if(!hasNext())
                return null;
            next = OneWayLinkedList.this.get(pos++);
            return next;
        }
    }

    public OneWayLinkedList() {
        // make a sentinel
        sentinel = new Element(null);
        size = 0;
        // TODO
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E element) {
        // TODO Auto-generated method stub
        try{
            add(size, element);
        }catch (NoSuchElementException ex){
            return false;
        }
        return true;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {
        if(index < 0 || index > size)
            throw new NoSuchElementException();
        Element last = sentinel;
        for(int i = 0; i < index; i ++){
            last = last.next;
        }
        Element toCopy = last.next;
        last.next = new Element(element);
        last.next.next = toCopy;
        size++;
    }

    @Override
    public void clear() {
        sentinel.next = null;
        size = 0;

    }

    @Override
    public boolean contains(E element) {
       return indexOf(element) != -1;
    }

    @Override
    public E get(int index) throws NoSuchElementException {
        if(index >= size)
            throw new NoSuchElementException();
        Element last = sentinel;
        for(int i = 0; i < index; i ++){
            last = last.next;
        }
        return last.next.object;
    }

    @Override
    public E set(int index, E element) throws NoSuchElementException {
        if(index > size)
            throw new NoSuchElementException();
        Element last = sentinel;
        for(int i = 0; i < index; i ++){
            last = last.next;
        }
        E prev =  last.next.object;
        last.next.object = element;
        return prev;
    }

    @Override
    public int indexOf(E element) {
        Element last = sentinel;
        int pos  = 0;
        while(last.next != null){
            if(last.next.object == element)
                return pos;
            last = last.next;
            pos++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == null;
    }

    @Override
    public E remove(int index) throws NoSuchElementException {
        if(index >= size())
            throw new NoSuchElementException();
        Element last = sentinel;
        for(int i = 0; i < index; i ++){
            last = last.next;
        }
        E prev = last.next.object;
        last.next = last.next.next;
        size--;
        return prev;
    }

    @Override
    public boolean remove(E element) {
        Element last = sentinel;
        while(last.next != null){
            if(last.next.object.equals(element)){
                last.next = last.next.next;
                size--;
                return true;
            }
            last = last.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    public void deleteEven(){
        Element toKeep = sentinel;
        while(toKeep.next != null){
            toKeep.next = toKeep.next.next;
            size--;
            if(toKeep.next != null)
                toKeep = toKeep.next;
        }
    }
}
