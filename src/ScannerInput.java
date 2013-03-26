import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScannerInput {
	static CharacterClass myClass = new CharacterClass();

	public static void main(String[] args) {

		File file = new File("sample_input_specification.txt");

		try {

			Scanner sc = new Scanner(file);
			Scanner post = new Scanner(file);

			while (sc.hasNextLine()) {

				String line = sc.nextLine();
				line = line.replaceAll("\\s+", "");

				if (line.contains("$DIGIT[")) {
					token(line, "DIGIT");
					System.out.println(myClass.DIGIT);
				}

				if (line.contains("$LETTER[")) {
					token(line, "LETTER");
					System.out.println(myClass.LETTER);
				}
			}
			
			while (post.hasNextLine()) {
				//String temp = "";
				String line = post.nextLine();
				line = line.replaceAll("\\s+", "");
				//if(line.contains("$")&&line.contains("[")){
				//Scanner l = new Scanner(line);
				//while(l.hasNext()){
					//String c = l.next();
					//while(c != " "){
						//temp+=c;
					//}
				//}
				//}

				if (line.contains("$NON-ZERO[") && myClass.DIGIT.size() > 0) {
					copy(myClass.DIGIT, myClass.NON_ZERO);
					for (int i = 0; i < myClass.NON_ZERO.size(); i++) {
						if (myClass.NON_ZERO.get(i) == '0')
							myClass.NON_ZERO.remove(i);
					}
					System.out.println(myClass.NON_ZERO);
				}

				if (line.contains("$SMALLCASE[")) {
					if (!line.contains("IN$LETTER")) {
						token(line, "SMALLCASE");

					} else {
						copy(myClass.LETTER, myClass.SMALLCASE);
						for (int i = 0; i < myClass.SMALLCASE.size(); i++) {
							if (Character.isUpperCase(myClass.SMALLCASE.get(i)))
								myClass.SMALLCASE.remove(i);
						}
					}
					System.out.println(myClass.SMALLCASE);
				}

				if (line.contains("$UPPERCASE[")) {
					if (!line.contains("IN$LETTER")) {
						token(line, "UPPERCASE");
						System.out.println(myClass.UPPERCASE);
					} else {
						copy(myClass.LETTER, myClass.UPPERCASE);
						for (int i = 0; i < myClass.UPPERCASE.size(); i++) {
							if (!Character.isUpperCase(myClass.UPPERCASE.get(i)))
								myClass.UPPERCASE.remove(i);
						}
					}
					System.out.println(myClass.UPPERCASE);
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void token(String s, String type) {
		String t = "";
		int c = 0;
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) == '[')
				c = i + 1;

		while (s.charAt(c) != ']') {
			if (s.charAt(c) != '-') {

				if (type == "DIGIT" && s.charAt(c + 1) != '-')
					myClass.DIGIT.add(s.charAt(c));
				else if (type == "SMALLCASE" && s.charAt(c + 1) != '-')
					myClass.SMALLCASE.add(s.charAt(c));
				else if (type == "LETTER" && s.charAt(c + 1) != '-')
					myClass.LETTER.add(s.charAt(c));
			} else {
				t += s.charAt(c - 1);
				c++;
				t += s.charAt(c);
				parse(t, type);
				t = "";
			}
			c++;
		}
	}

	public static void parse(String s, String type) {
		for (char i = s.charAt(0); i <= s.charAt(1); i++) {
			if (type == "DIGIT")
				myClass.DIGIT.add(i);
			else if (type == "SMALLCASE")
				myClass.SMALLCASE.add(i);
			else if (type == "LETTER")
				myClass.LETTER.add(i);
		}
	}

	public static void copy(ArrayList<Character> x, ArrayList<Character> y) {
		for (int i = 0; i < x.size(); i++)
			y.add(x.get(i));
	}
}