import java.util.ArrayList;
import java.util.Collections;

public class test {
    static int stars = 0;
    public static void main (String [] args){

        solution("abc", "bkb");
    }

    public static int solution(String P, String Q){
        // Implement your solution here
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int []  results1 = new int[alphabet.length()];
        for(int i = 0; i < P.length(); i++){
            for(int j = 0; j < alphabet.length(); j++){
                if(alphabet.charAt(j) == P.charAt(i)) {
                    results1[j] += 1;
                    break;
                }

            }
        }

        int []  results2 = new int[alphabet.length()];
        for(int i = 0; i < Q.length(); i++){
            for(int j = 0; j < alphabet.length(); j++){
                if(alphabet.charAt(j) == Q.charAt(i)) {
                    results2[j] += 1;
                    break;
                }

            }
        }

        String s= String.join("", Collections.nCopies(P.length(), String.valueOf("*")));
        while(true) {
            int[] max1 = {results1[0]};
            char[] letter1 = {P.charAt(0)};
            int id1 = mostFreqLetter(letter1, max1, results1, alphabet);


            char[] letter2 = {Q.charAt(0)};
            int [] max2 = {results2[0]};
            int id2 = mostFreqLetter(letter2, max2, results2, alphabet);

            stars = 0;
            if(chooseLeft(max1, max2)){
                s = appendString( letter1[0], P, Q, s);
                results1[id1] = 0;
            }
            else{
                s = appendString( letter2[0], Q, P, s);
                results2[id2] = 0;
            }
            if (stars == 0)
                break;
        }
        System.out.println(s);
        int []  results = new int[alphabet.length()];
        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < alphabet.length(); j++){
                if(alphabet.charAt(j) == s.charAt(i)) {
                    results[j] += 1;
                    break;
                }

            }
        }
        int count = 0;
        for(int i = 0; i < results.length; i++){
                if(results[i] != 0)
                    count++;
        }
        System.out.println(count);
        return 0;
    }

    static int mostFreqLetter(char[] let, int[] occurs, int[] res, String alphabet){
        int id1 = 0;
        occurs[0] = 0;
        for(int i = 0; i < res.length; i++){
            if(occurs[0] < res[i]) {
                occurs[0] = res[i];
                id1 = i;
                let[0] = alphabet.charAt(i);
            }
        }
        return id1;
    }

    static boolean chooseLeft(int[] m1, int[] m2){
        if(m2[0]<m1[0]) {
            return true;
        }
        else return false;
    }
    static String appendString( char fill, String first, String second, String s){
        for(int i = 0; i < first.length(); i++){
            if(first.charAt(i) == fill)
                s = s.substring(0, i) + fill+ s.substring(i + 1);


        }
        for(int i = 0; i < second.length(); i++){
            if(second.charAt(i) == fill)
                s = s.substring(0, i) + fill+ s.substring(i + 1);
            if(s.charAt(i) == '*')
                stars++;

        }
        return s;
    }
}
