package lab10;

import java.util.Scanner;
import java.util.*;

public class Document implements IWithName{
	public String name;
	// TODO? You can change implementation of Link collection
	public SortedMap<String,Link> link;
	
	public Document(String name) {
		this.name=name.toLowerCase();
		link=new TreeMap<String,Link>();
	}

	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new TreeMap<String,Link>();
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
						Link l = createLink(linkInfo);
						if(!link.containsKey(l.ref))
							link.put(l.ref, l);
					}
				}
			}
		}while(!line.equals("eod"));
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

	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
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
		String result ="";
		for( Link l : link.values()){
			result += l.toString() + ", ";
		}

		if(!result.equals("")) {
			retStr += "\n" + result;
			retStr = retStr.substring(0, retStr.length() - 2);
		}
		return retStr;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String getName() {
		return name;
	}
}
