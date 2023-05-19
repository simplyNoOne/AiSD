package lab456;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            this.object = e;
        }
        public Element(E e, Element next, Element prev) {
            //TODO
            this.object = e;
            this.next = next;
            this.prev = prev;
        }
        // add element e after this
        public void addAfter(Element elem) {
            //TODO
            elem.next = this.next;
            this.next.prev = elem;
            elem.prev = this;
            this.next = elem;
        }
        // assert it is NOT a sentinel
        public void remove() {
            //TODO
            if(object != null) {
                this.prev.next = this.next;
                this.next.prev = this.prev;
            }
        }
        E object;
        Element next=null;
        Element prev=null;
    }

    Element sentinel;
    int size;

    private class InnerIterator implements Iterator<E>{
        //TODO
        public InnerIterator() {
            //TODO
        }
        @Override
        public boolean hasNext() {
            //TODO
            return false;
        }

        @Override
        public E next() {
            //TODO
            return null;
        }
    }

    private class InnerListIterator implements ListIterator<E>{
        //TODO
        Element el;
        public InnerListIterator() {
            //TODO
            el = sentinel;
        }
        @Override
        public boolean hasNext() {
            //TODO
            return el != sentinel.prev;
        }

        @Override
        public E next() {
            //TODO
            el = el.next;
            return el.object;
        }
        @Override
        public void add(E arg0) {
            throw new UnsupportedOperationException();
        }
        @Override
        public boolean hasPrevious() {
            //TODO
            return el != sentinel.next;
        }
        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }
        @Override
        public E previous() {
            el = el.prev;
            return el.object;
        }
        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        @Override
        public void set(E arg0) {
            throw new UnsupportedOperationException();
        }
    }
    public TwoWayCycledOrderedListWithSentinel() {
        //TODO
        sentinel = new Element(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        //TODO
        Element el = sentinel;
        while(el.next != sentinel && ((Comparable<E>)el.next.object).compareTo(e) <= 0){
            el = el.next;
        }
        Element toAdd = new Element(e, el.next, el);
        el.next.prev = toAdd;
        el.next = toAdd;
        size++;
        return true;
    }

    private Element getElement(int index) {
        if(index >= size)
            return null;
        Element el = sentinel;
        for(;index >= 0; index--)
            el = el.next;
        return el;
    }

    private Element getElement(E obj) {
        Element el = sentinel;
        while(el.next != sentinel){
            el = el.next;
            if(el.object.equals(obj))
                return el;
        }
        return null;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public E get(int index) {
        Element e = getElement(index);
        if(e == null)
            throw new NoSuchElementException();
        return e.object;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(E element) {
        //TODO
        Element el = sentinel;
        int index = -1;
        while(el.next != sentinel){
            index++;
            el = el.next;
            if(el.object.equals(element))
                return index;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new InnerListIterator();
    }

    @Override
    public E remove(int index) {
        //TODO
        Element e = getElement(index);
        if(e == null)
            throw new NoSuchElementException();
        e.prev.next = e.next;
        e.next.prev = e.prev;
        size--;
        return e.object;
    }

    @Override
    public boolean remove(E e) {
        //TODO
        Element el = getElement(e);
        if(el == null)
            return false;
        el.next.prev = el.prev;
        el.prev.next = el.next;
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
        //TODO
        if(other == this)
            return;
        if(other.isEmpty())
            return;
        Element oel = other.sentinel.next;
        Element el = this.sentinel;
        while(oel!= other.sentinel){
            if(el.next == this.sentinel || ((Comparable<E>)el.next.object).compareTo(oel.object) > 0) {
                oel = oel.next;
                el.next.prev = oel.prev;
                oel.prev.next = el.next;
                oel.prev.prev = el;
                el.next = oel.prev;
                size++;
            }
            else
                el = el.next;
        }
        other.clear();
    }

    //@SuppressWarnings({ "unchecked", "rawtypes" })
    public void removeAll(E e) {
        //TODO
        boolean found = false;
        Element el = sentinel;
        while(el.next != sentinel){
            el = el.next;
            if(el.object.equals(e)) {
                found = true;
                el.next.prev = el.prev;
                el.prev.next = el.next;
            }
            else if(found)
                break;
        }
    }




}
