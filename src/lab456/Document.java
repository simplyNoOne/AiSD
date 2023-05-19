package lab456;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Document{
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> link;
    public Document(String name, Scanner scan) {
        this.name=name.toLowerCase();
        link=new TwoWayCycledOrderedListWithSentinel<Link>();
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
        //TODO
        ListIterator<Link> iter=link.listIterator();
        while(iter.hasNext()){
            retStr += "\n";
            for(int i = 0; i < 10; i++){
                if(!iter.hasNext())
                    break;
                retStr+=(iter.next().toString() + " ");
            }
            retStr = retStr.substring(0, retStr.length() - 1);
        }
        return retStr;
    }

    public String toStringReverse() {
        String retStr="Document: "+name;
        ListIterator<Link> iter=link.listIterator();
        while(iter.hasPrevious()){
            retStr += "\n";
            for(int i = 0; i < 10; i++){
                if(!iter.hasPrevious())
                    break;
                retStr+=(iter.previous().toString() + " ");
            }
            retStr = retStr.substring(0, retStr.length() - 1);
        }
        return retStr;
    }

    public int[] getWeights() {
        //TODO
        Iterator<Link> it = link.listIterator();
        int[] weights = new int[link.size()];
        for(int i =0; it.hasNext(); i++){
            weights[i] = it.next().weight;
        }
        return weights;
    }

    public static void showArray(int[] arr) {
        // TODO
        if(arr.length==0)
            return;
        StringBuilder res = new StringBuilder();
        for(int i =0; i < arr.length; i++){
            res.append(arr[i]);
            if(i != arr.length-1)
                res.append(" ");
        }
        System.out.println(res);
    }

    void bubbleSort(int[] arr) {
        showArray(arr);
        for(int i =0; i< arr.length-1; i++){
            for(int j = arr.length - 1; j > i; j--){
                if(arr[j]<arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
            showArray(arr);
        }
    }

    public void insertSort(int[] arr) {
        showArray(arr);
        for(int i = arr.length-2; i>=0; i--){
            int j = i+1;
            while(j + 1< arr.length && arr[i]>arr[j])j++;
            if(j != arr.length-1 || arr[i]<=arr[j])
                j--;
            int temp = arr[i];
            System.arraycopy(arr, i+1, arr, i, j-i);
            arr[j] = temp;
            showArray(arr);
        }
    }
    public void selectSort(int[] arr) {
        showArray(arr);
        for(int i =1; i< arr.length; i++) {
            int max = arr[0];
            int id = 0;
            for (int j = 0; j <= arr.length - i; j++){
                if(max < arr[j]) {
                    max = arr[j];
                    id = j;
                }
            }
            arr[id] = arr[arr.length - i];
            arr[arr.length - i] = max;
            showArray(arr);
        }
    }


    public void iterativeMergeSort(int[] arr) {
        showArray(arr);
        int n = arr.length;
        if (n == 0) {
            return;
        }
        for (int subLen = 1; subLen < n; subLen *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * subLen) {
                int mid = Math.min(leftStart + subLen - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * subLen - 1, n - 1);
                merge(arr, leftStart, mid, rightEnd);
            }
            showArray(arr);
        }
    }

    public static void merge(int[] arr, int leftStart, int mid, int rightEnd){
        int leftEnd = mid;
        int rightStart = mid + 1;
        int index = 0;
        int size = rightEnd - leftStart + 1;
        int[] tmp = new int[size];
        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (arr[leftStart] <= arr[rightStart]) {
                tmp[index++] = arr[leftStart++];
            } else {
                tmp[index++] = arr[rightStart++];
            }
        }
        while (leftStart <= leftEnd) {
            tmp[index++] = arr[leftStart++];
        }
        while (rightStart <= rightEnd) {
            tmp[index++] = arr[rightStart++];
        }
        for (int i = 0; i < size; i++, rightEnd--) {
            arr[rightEnd] = tmp[size - 1 - i];
        }
    }

    public void radixSort(int[] arr) {
        showArray(arr);
        for(int i = 1; i <= 3; i++){
            innerSort(arr, i);
            showArray(arr);
        }
    }

    public static void innerSort(int[] arr, int atPos){
        int pos1 = (int)Math.pow(10, atPos);
        int pos2 = (int)Math.pow(10, atPos - 1);
        int k = 10;
        int[] pos = new int[k];
        for (int i : arr) {
            pos[digit(i, pos1, pos2)]++;
        }
        pos[0]--;
        for(int i = 1; i < k; i++)
            pos[i] += pos[i - 1];
        int[] res = new int[arr.length];
        for(int i = arr.length - 1; i >= 0;  i--){
            res[pos[digit(arr[i], pos1, pos2)]] = arr[i];
            pos[digit(arr[i], pos1, pos2)]--;
        }
        System.arraycopy(res, 0 , arr, 0, arr.length);
    }

    public static int digit(int num, int pos1, int pos2){
        return ((num % pos1 )/ pos2);
    }

}
