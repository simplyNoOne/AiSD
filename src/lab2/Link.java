package lab2;
public class Link{
    public String ref;
    public Link(String ref) {
        this.ref=ref;
    }

    public String toString(){
        return ref;
    }

    public boolean equals(Object o){
        Link l = (Link) o;
        if(l == null)
            return false;
        if(this.ref.equals(l.ref))
            return true;
        return false;
    }

    // in the future there will be more fields
}