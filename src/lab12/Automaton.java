package lab12;

import java.util.LinkedList;

public class Automaton implements IStringMatcher {

	@Override
	public LinkedList<Integer> validShifts(String pattern, String text) {

		int[] bounds = getCode(pattern, text);
		int m = pattern.length();
		int function[][] = new int[m + 1][1 + bounds[1] - bounds[0]];

		prepTransitionFunc(pattern, function, bounds[0]);


		LinkedList<Integer> shifts = new LinkedList<>();
		int n = text.length();
		int q = 0;
		for(int i =0 ; i < n; i++){
			q = function[q][(int)text.charAt(i) - bounds[0]];
			if( q == m){
				shifts.add(i - m + 1);
			}
		}

		return shifts;
	}

	int[] getCode(String pattern, String text) {
		int min = Integer.MAX_VALUE;
		int max = 0;
		for( int i = 0; i < pattern.length(); i++) {
			if((int)pattern.charAt(i) < min) {
				min = (int)pattern.charAt(i);
			}
			if((int)pattern.charAt(i) > max) {
				max = (int)pattern.charAt(i);
			}
		}

		for( int i = 0; i < text.length(); i++) {
			if((int)text.charAt(i) < min) {
				min = (int)text.charAt(i);
			}
			if((int)text.charAt(i) > max) {
				max = (int)text.charAt(i);
			}
		}
		int[] bounds = new int[2];
		bounds[0] = min;
		bounds[1] =max;
		return bounds;

	}


	private void prepTransitionFunc (String pattern, int[][] function, int min){
		int m = pattern.length();
		for( int i = 0; i <= m; i++) {
			for( int j = 0; j < function[0].length; j++) {
				int k = Math.min( m + 1, i + 2);
				do {
					k--;
				}while(!(pattern.substring(0, i) + (char)(j + min)).endsWith(pattern.substring(0, k)));
				function[i][j] = k;
			}
		}
	}

}
