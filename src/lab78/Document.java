package lab78;

import java.util.Scanner;

public class Document implements IWithName{
	int MODVALUE = 100_000_000;

	public String name;
	public BSTgood<Link> link;
	public Document(String name) {
		this.name=name.toLowerCase();
		link=new BSTgood<Link>();
	}

	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new BSTgood<Link>();
		load(scan);
	}
	public void load(Scanner scan) {
		//TODO
		String line;
		do {
			line = scan.nextLine();
			String[] word = line.split(" ");
			for (int i = 0; i < word.length; i++) {
				if (word[i].length() > 5 && word[i].substring(0, 5).equalsIgnoreCase("link=")) {
					String linkInfo = word[i].substring(5);
					if (isCorrectLink(linkInfo)) {
						link.add(createLink(linkInfo));
					}
				}
			}
		}while(!line.equals("eod"));
	}

	public boolean addSingle(String linkName){
		if(!isCorrectLink(linkName))
			return false;
		link.add(createLink(linkName));
		return true;
	}

	public static Link safeCreateLink(String linkName){
		if(!isCorrectLink(linkName))
			return null;
		return createLink(linkName);
	}
	public static boolean isCorrectLink(String id){
		if(!Character.isAlphabetic(id.charAt(0)))
			return false;
		id+="check";
		String[] parts = id.split("[\\(||\\)]");
		if(parts.length != 1 && parts.length != 3)
			return false;
		for(int c = 1; c < parts[0].length(); c++){
			if(!Character.isLetterOrDigit(parts[0].charAt(c)) && parts[0].charAt(c) != '_')
				return false;
		}
		if(parts.length == 3) {
			if(!parts[2].equals("check"))
				return false;
			try {
				if(Integer.parseInt(parts[1]) <= 0)
					return false;
			}catch (NumberFormatException e){return false;}
		}
		return true;
	}


	public static boolean isCorrectId(String id) {
		if(!Character.isAlphabetic(id.charAt(0)))
			return false;
		return true;
	}

	// accepted only small letters, capitalletter, digits nad '_' (but not on the begin)
	private static Link createLink(String link) {
		String[] parts = link.split("[\\(||\\)]");
		parts[0] = parts[0].toLowerCase();
		Link toRet;
		if(parts.length == 1)
			toRet = new Link(parts[0]);
		else
			toRet = new Link(parts[0], Integer.parseInt(parts[1]));
		return toRet;
	}

	@Override
	public String toString() {
		String retStr="Document: "+name;
		String result =link.toStringInOrder();
		if(!result.equals("")) {
			retStr += "\n" + result;
			retStr = retStr.substring(0, retStr.length() - 2);
		}
		return retStr;
	}

	public String toStringPreOrder() {
		String retStr="Document: "+name;
		String result =link.toStringPreOrder();
		if(!result.equals("")) {
			retStr += "\n" + result;
			retStr = retStr.substring(0, retStr.length() - 2);
		}
		return retStr;
	}

	public String toStringPostOrder() {
		String retStr="Document: "+name;
		String result = link.toStringPostOrder();
		if(!result.equals("")) {
			retStr +=  "\n" + result;
			retStr = retStr.substring(0, retStr.length() - 2);
		}
		return retStr;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object other){
		if (other.getClass() != getClass())
			return false;
		return name.equals(((Document) other).name);
	}

	@Override
	public int hashCode(){
		int [] arr = {7, 11, 13, 17, 19};
		int s = arr.length;
		int sum = (int)name.charAt(0);
		for(int i = 1; i < name.length(); i++){
			sum = ((sum * arr[((i - 1) % s)])%MODVALUE + (int)name.charAt(i))%MODVALUE;
		}
		return sum;
	}
}

