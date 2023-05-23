package lab10;

import java.security.KeyPair;
import java.util.*;
import java.util.Map.Entry;

public class Graph {
	int arr[][];
	int p[][];
	int d[][];
	//TODO? Collection to map Document to index of vertex 
	// You can change it
	HashMap<String,Integer> name2Int;
	@SuppressWarnings("unchecked")
	//TODO? Collection to map index of vertex to Document
	// You can change it
	Entry<String, Document>[] arrDoc=(Entry<String, Document>[])new Entry[0];
	
	// The argument type depend on a selected collection in the Main class

	public Graph(SortedMap<String,Document> internet){
		int size=internet.size();
		arr=new int[size][size];
		d = new int[size][size];
		// TODO

		name2Int = new HashMap<>();
		arrDoc = new Entry[size];
		int id = 0;
		for (Document doc : internet.values()){
			name2Int.put(doc.name, id);
			arrDoc[id++] = new AbstractMap.SimpleEntry<>(doc.name, doc);
		}

		for (int i = 0; i < size; i++) {
			Arrays.fill(arr[i], Integer.MAX_VALUE);
		}

		for (int i = 0; i < arrDoc.length; i++) {
			arr[i][i] = 0;
			for (Link link : ((Document)((Entry)arrDoc[i]).getValue()).link.values()) {
				int target = name2Int.get(link.ref);
				arr[i][target] = link.weight;
			}
		}

	}

	public String bfs(String start) {
		// TODO
		if(arr.length == 0)
			return null;
		int pos;
		try {
			pos = name2Int.get(start);
		}catch(NullPointerException ex){return null;}
		boolean[] visited = new boolean[arr.length];
		String result = "";

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(pos);
		visited[pos] = true;

		while (!queue.isEmpty()) {
			int id = queue.poll();
			Document current= arrDoc[id].getValue();
			result += (current.getName()) + ", ";

			for (int i = 0; i < arr.length; i++) {
				if (arr[id][i] != Integer.MAX_VALUE && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}

		result = result.substring(0, result.length() - 2);
		return result;
	}


public String dfs(String start) {

	if (arr.length == 0)
		return null;
	int pos;
	try {
		pos = name2Int.get(start);
	} catch (NullPointerException ex) {
		return null;
	}
	boolean[] visited = new boolean[arr.length];
	String result = actualDFS(pos, visited);
	if (result.length() > 2) {
		result = result.substring(0, result.length() - 2);
	}
	return result;
}

public String actualDFS(int pos,  boolean[] visited){
	Document current = arrDoc[pos].getValue();
	String res = current.getName() + ", ";
	visited[pos] = true;
	for (int i = 0; i < arr.length; i++) {
		if (arr[pos][i] != Integer.MAX_VALUE && !visited[i]) {
			res += actualDFS(i,  visited);
		}
	}
	return res;
}


	public int connectedComponents() {
		// TODO
		DisjointSetForest forest = new DisjointSetForest(arrDoc.length);
		for(int i = 0; i < arrDoc.length; i++){
			for( int j = 0; j < arrDoc.length; j++){
				if( arr[i][j] != Integer.MAX_VALUE)
					forest.union(j, i);
			}
		}
		return forest.countSets();
	}
}
