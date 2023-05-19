package lab1;

import java.util.Scanner;

public class Document {
    public static void loadDocument(String name, Scanner scan) {
        //TODO
        String line;
        do {
            line = scan.nextLine();

            String[] word = line.split(" ");
            for (int i = 0; i < word.length; i++) {
                if (word[i].length() > 5 && word[i].substring(0, 5).toLowerCase().equals("link=")) {
                    String link = word[i].substring(5);
                    if (correctLink(link)) {
                        System.out.println(link.toLowerCase());
                    }
                }
            }
        }while(!line.equals("eod"));

    }


    // accepted only small letters, capital letter, digits nad '_' (but not on the beginning)
    public static boolean correctLink(String link) {
        if(!Character.isAlphabetic(link.charAt(0)))
            return false;
        for(int c = 1; c < link.length(); c++){
            if(!Character.isLetterOrDigit(link.charAt(c)) && link.charAt(c) != '_')
                return false;
        }
        return true;
    }

}