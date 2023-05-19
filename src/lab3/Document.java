package lab3;

import lab3.TwoWayUnorderedListWithHeadAndTail;

import java.util.Scanner;

public class Document{
    public String name;
    public TwoWayUnorderedListWithHeadAndTail<Link> link;
    public Document(String name, Scanner scan) {
        this.name = name;
        link = new TwoWayUnorderedListWithHeadAndTail<>();
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
                    String resLink = word[i].substring(5);
                    if (correctLink(resLink)) {
                        link.add(new Link(resLink.toLowerCase()));
                    }
                }
            }
        }while(!line.equals("eod"));
    }
    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    private static boolean correctLink(String link) {
        if(!Character.isAlphabetic(link.charAt(0)))
            return false;
        for(int c = 1; c < link.length(); c++){
            if(!Character.isLetterOrDigit(link.charAt(c)) && link.charAt(c) != '_')
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "Document: " + name;
        for(int i = 0; i < link.size(); i++)
            result += ("\n" + link.get(i).toString());
        return result;
    }

    public String toStringReverse() {
        String retStr="Document: "+name;
        return retStr+link.toStringReverse();
    }

}