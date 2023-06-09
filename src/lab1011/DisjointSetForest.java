package lab1011;

import java.util.HashSet;

public class DisjointSetForest implements DisjointSetDataStructure {
	
	class Element{
		int rank;
		int parent;
		Element(){}
		Element(int parent, int rank){this.parent= parent; this.rank = rank;}
	}

	Element[]arr;

	public DisjointSetForest(int size) {
		//TODO
		arr = new Element[size];
		for ( int i = 0; i < arr.length; i++) {
			makeSet(i);
		}
	}

	@Override
	public void makeSet(int item) {
		//TODO
		arr[item] = new Element(item, 0);

	}

	@Override
	public int findSet(int item) {
		//TODO
		if(arr[item].parent == item)
			return item;
		int toRet = findSet(arr[item].parent);
		arr[item].parent = toRet;
		return toRet;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		//TODO
		int repA = findSet(itemA);
		int repB = findSet(itemB);
		if (repA == repB)
			return false;
		if (arr[repA].rank > arr[repB].rank){
			arr[repB].parent = repA;
		}
		else {
			arr[repA].parent = repB;
			if (arr[repA].rank == arr[repB].rank)
				arr[repB].rank++;
		}

		return true;
	}

	@Override
	public String toString() {
		//TODO
		String str = "Disjoint sets as forest:\n";
		for(int i =0; i < arr.length; i++)
			str += i + " -> " +arr[i].parent + "\n";
		str = str.substring(0, str.length() - 1);
		return str;
	}

	@Override
	public int countSets() {
		// TODO
		HashSet<Element> reps= new HashSet<>();
		for(int i =0; i < arr.length; i++){
			if(!reps.contains(arr[arr[i].parent])){
				reps.add(arr[arr[i].parent]);
			}
		}
		return reps.size();
	}
}
