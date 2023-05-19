package lab3;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E>{

    private class Element{
        public Element(E e) {

            // TODO
            this.object = e;
        }
        public Element(E e, Element next, Element prev) {
            //TODO
            this.object = e;
            this.next = next;
            this.prev = prev;
        }
        E object;
        Element next=null;
        Element prev=null;
    }

    Element head;
    Element tail;
    int size;

    private class InnerIterator implements Iterator<E>{
        Element pos;
        // TODO maybe more fields....

        public InnerIterator() {
            //TODO
            pos = head;
        }
        @Override
        public boolean hasNext() {
            //TODO
            return( pos != tail);
        }

        @Override
        public E next() {
            //TODO
            if(pos!= tail && pos == null)
                pos = head;
            if(!hasNext())
                return null;
            return pos.object;
        }
    }

    private class InnerListIterator implements ListIterator<E>{
        Element p = null;
        boolean bWasNext = false;
        boolean bWasPrev= false;
        // TODO maybe more fields....

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();

        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return (p!=tail );
        }

        @Override
        public boolean hasPrevious() {
            // TODO Auto-generated method stub
            return (p != head);
        }

        @Override
        public E next() {
            // TODO Auto-generated method stub
            if(!hasNext())
                return null;
            if (p == null)
                p = head;
            else
                p =p.next;
            bWasNext = true;
            bWasPrev =false;
            return p.object;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            // TODO Auto-generated method stub
            if(!hasPrevious())
                return null;
            if (p == null)
                p = tail;
            else
                p = p.prev;
            bWasPrev = true;
            bWasNext = false;
            return p.object;
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
        public void set(E e) {
            // TODO Auto-generated method stub
            if(p==null)
               return;
            p.next.object = e;
        }
    }

    public TwoWayUnorderedListWithHeadAndTail() {
        // make a head and a tail
        head=null;
        tail=null;
        size = 0;
    }

    @Override
    public boolean add(E e) {
        //TODO
        size++;
        Element nEl = new Element(e);
        if(head == null) {
            head = tail = nEl;
            return true;
        }
        tail.next = nEl;
        nEl.prev = tail;
        tail = nEl;
        return true;
    }

    @Override
    public void add(int index, E element) {
        //TODO
        if(index>size)
            throw new NoSuchElementException();
        size++;
        Element nEl = new Element(element);
        Element e = head;
        for(; index >0; index--){
            e = e.next;
        }
        if(e == null)
        {
            if(head == null)
                head = nEl;
            else {
                tail.next = nEl;
                nEl.prev = tail;
            }
            tail = nEl;
        }
        else {
            if(e == head)
                head = nEl;
            else
                e.prev.next = nEl;
            nEl.next = e;
            nEl.prev = e.prev;
            e.prev = nEl;
        }
    }

    @Override
    public void clear() {
        //TODO
        size = 0;
        head = tail = null;
    }

    @Override
    public boolean contains(E element) {
        //TODO
        return indexOf(element) != -1;
    }

    @Override
    public E get(int index) {
        //TODO
        if(index >= size)
            throw new NoSuchElementException();
        Element el = head;
        for(; index > 0; index--){
            el = el.next;
        }
        return el.object;
    }

    @Override
    public E set(int index, E element) {
        if(index >= size)
            throw new NoSuchElementException();
        Element el = head;
        for(; index > 0; index--){
            el = el.next;
        }
        E prev = el.object;
        el.object = element;
        return prev;
    }

    @Override
    public int indexOf(E element) {
        //TODO
        int index = 0;
        Element el = head;
        while(el != null) {
            if(el.object.equals(element))
                return index;
            el = el.next;
            index++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
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
    public E remove(int index) {
        if(index >= size)
            throw new NoSuchElementException();
        size--;
        Element rem = head;
        for(; index>0; index--){
            rem = rem.next;
        }
        if(rem == head) {
            head = rem.next;
            if(head!= null)
                head.prev = null;
        }
        else
            rem.prev.next = rem.next;
        if(rem == tail) {
            tail = rem.prev;
            if(tail != null)
                tail.next = null;
        }
        else
            rem.next.prev = rem.prev;
        return rem.object;
    }

    @Override
    public boolean remove(E e) {
        Element rem = head;
        while(rem != null)
        {
            if(rem.object.equals( e )){
                if(rem == head) {
                    head = rem.next;
                    if(head!= null)
                        head.prev = null;
                }
                else
                    rem.prev.next = rem.next;
                if(rem == tail) {
                    tail = rem.prev;
                    if(tail != null)
                        tail.next = null;
                }
                else
                    rem.next.prev = rem.prev;
                size--;
                return true;
            }
            rem = rem.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }
    public String toStringReverse() {
        if(size == 0)
            return "";
        ListIterator<E> iter=new InnerListIterator();
        String retStr="";;
        while(iter.hasPrevious())
        {
            E obj = iter.previous();
            if(obj != null)
                retStr += ("\n"+obj.toString());
        }
        return retStr;
    }

    public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
        //TODO
        if(other.equals(this))
            return;
        if(this.head == null){
            this.head = other.head;
        }else
            this.tail.next = other.head;
        if(other.head != null)
            other.head.prev = this.tail;
        if(other.tail != null)
            this.tail = other.tail;
        size += other.size;
        other.clear();
    }

    public void deleteEven(){
        Element el = head;
        while(el!=null){
            if(el!= head)
                el.prev.next = el.next;
            else
                head = el.next;
            if(el!=tail)
                el.next.prev = el.prev;
            else
                tail = el.prev;
            el = el.next;
            size--;
            if(el!=null)
                el = el.next;
        }

    }
}
