package lab9;

public class DisjointSetLinkedList implements DisjointSetDataStructure {

	private class Element{
		int representant;
		int next;
		int length;
		int last;

		Element(){}
		Element(int repres){this.representant= repres; this.length = 1; this.last = repres; this.next = NULL;}
	}
	
	private static final int NULL=-1;
	
	Element arr[];
	
	public DisjointSetLinkedList(int size) {
		//TODO
		arr = new Element[size];
		for(int i = 0; i < arr.length; i++){
			makeSet(i);
		}
	}
	
	@Override
	public void makeSet(int item) {
		//TODO
		arr[item] = new Element(item);

	}

	@Override
	public int findSet(int item) {
		//TODO
		if(item >= arr.length)
			return -1;
		return arr[item].representant;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		//TODO
		int repA = findSet(itemA);
		int repB = findSet(itemB);
		if(repA == repB)
			return false;
		int el, rep = NULL;

		if (arr[repA].length >= arr[repB].length){
			el = repB;
			rep = repA;
		}
		else{
			el = repA;
			rep = repB;
		}
		arr[arr[rep].last].next = el;
		arr[rep].last = arr[el].last;
		arr[rep].length += arr[el].length;
		while(el != NULL){
			arr[el].representant = rep;
			el = arr[el].next;
		}
		return true;
	}

	
	@Override
	public String toString() {
		//TODO
		String str = "Disjoint sets as linked list:\n";
		for(int i = 0; i < arr.length; i++){
			int el = findSet(i);
			if(i == el) {
				while (el != NULL) {
					str += el + ", ";
					el = arr[el].next;
				}
				str = str.substring(0, str.length() - 2);
				str+="\n";
			}
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

}
