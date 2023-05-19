package lab456;

public class Link implements Comparable<Link>{
    public String ref;
    public int weight;
    public Link(String ref) {
        this.ref=ref;
        weight=1;
    }

    public Link(String ref, int weight) {
        this.ref=ref;
        this.weight=weight;
    }

    @Override
    public boolean equals(Object obj) {
        //TODO
        if(!this.getClass().equals(obj.getClass()))
            return false;
        Link another = (Link)obj;
        return this.ref.equals(another.ref);
    }

    @Override
    public String toString() {
        return ref+"("+weight+")";
    }

    @Override
    public int compareTo(Link another) {
        return this.ref.compareTo(another.ref);
    }
}
