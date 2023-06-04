package lab12;

import java.util.LinkedList;

public class KMP implements IStringMatcher {

	@Override
	public LinkedList<Integer> validShifts(String pattern, String text) {
		int n = text.length();
		int m = pattern.length();
		int[][] function = new int[2][m];

		prepPrefixFunc(pattern, function);

		LinkedList<Integer> shifts = new LinkedList<>();

		int q = 0;
		for( int i = 0; i < n; i++){
			while(q > 0 && function[0][q] != text.charAt(i)) {
				q = function[1][q -1 ];
			}
			if(function[0][q] == text.charAt(i)){
				q ++;
			}
			if(q == m){
				shifts.add(i - m + 1);
				q = function[1][q -1];
			}

		}

		return shifts;
	}

	private void prepPrefixFunc(String pattern, int [][] function){
		int m = pattern.length();
		function[1][0] = 0;
		for( int i = 0; i < m; i++){
			function[0][i] = pattern.charAt(i);
		}


		int k = 0;
		for(int i = 1; i < m; i++){
			while(k > 0 && (function[0][k] != function[0][i])) {
				k = function[1][k - 1];
			}
			if(function[0][k ] == function[0][i]){
				k++;
			}
			function[1][i] = k;

		}
	}


}
