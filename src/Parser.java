import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
	
	List<Terminal> terminals = new ArrayList<Terminal>();
	List<NonTerminal> nonTerminals = new ArrayList<NonTerminal>();
	
	public Parser() {
		File inFile = new File("grammar.txt");
		
		try {
			Scanner in = new Scanner(inFile);
			
			while(in.hasNextLine()) {
				String s = in.nextLine();
				char[] chars = s.toCharArray();
				
				for(int i = 0; i < chars.length; i++) {
					if(chars[i] == '<') {
						NonTerminal nt = new NonTerminal();
						while(chars[i] != '>') {
							nt.addToText(chars[i++]);
						}
						nt.addToText('>');
						System.out.println(nt.toString());
					}
				}
				
				/*Pattern tokenPattern = Pattern.compile("\\<(.+?)\\>");
				Matcher m = tokenPattern.matcher(s);
				if(m.matches()) {
					NonTerminal nt = new NonTerminal(m.group());
					nonTerminals.add(nt);
				} else {
					System.out.println("bills suck");
					continue;
				}*/
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		/*for(NonTerminal non : nonTerminals)
			System.out.println(non.toString());*/
	}
	
	public static void main(String[] args) {
		Parser p = new Parser();
	}
}
