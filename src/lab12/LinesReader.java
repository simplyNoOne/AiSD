package lab12;

import java.util.Scanner;

public class LinesReader {
		String concatLines(int howMany, Scanner scanner) {
			int i =0;
			StringBuilder builder = new StringBuilder();

			while(i < howMany){
				builder.append(scanner.nextLine());
				i++;
			}
			return builder.toString();
		}

}
