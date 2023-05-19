package lab78;

import java.util.LinkedList;

public class HashTable{
	LinkedList arr[]; // use pure array
	private final static int defaultInitSize=8;
	private final static double defaultMaxLoadFactor=0.7;
	private int size;
	private int elements = 0;
	private final double maxLoadFactor;

	public HashTable() {
		this(defaultInitSize);
	}
	public HashTable(int size) {
		this(size,defaultMaxLoadFactor);
	}


	public HashTable(int initCapacity, double maxLF) {
		arr = new LinkedList[initCapacity];
		this.maxLoadFactor=maxLF;
		size = initCapacity;
	}

	@SuppressWarnings("unchecked")
	public boolean add(Object elem) {
		if ( get(elem) != null)
			return false;
		if( (float)(elements + 1)/size > maxLoadFactor)
			doubleArray();
		int pos = elem.hashCode() % size;
		if ( arr[pos] == null)
			arr[pos] = new LinkedList();
		arr[pos].add(elem);
		elements++;
		return true;
	}


	@SuppressWarnings("unchecked")
	private void doubleArray() {
		//TODO
		LinkedList[] tempArr = arr;
		size = size * 2;
		arr = new LinkedList[size];
		elements = 0;
		for( int i = 0; i < tempArr.length; i++){
			if(tempArr[i] == null)
				continue;
			tempArr[i].forEach(this::add);
		}
	}


	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for ( int i = 0; i < size; i++){
			str.append(i + ":");
			if(arr[i] != null && arr[i].size() > 0){
				for( Object el : arr[i]){
					str.append(" " + ((IWithName)el).getName() + ",");
				}
				str.replace(str.length() - 1, str.length(), "\n");
			}else
				str.append("\n");
		}
		return str.toString();
	}

	public Object get(Object toFind) {
		int pos = toFind.hashCode() % size;
		if ( arr[pos] == null)
			return null;
		for ( Object el :arr[pos]){
			if (el.equals(toFind))
				return el;
		}
		return null;
	}
	
}

