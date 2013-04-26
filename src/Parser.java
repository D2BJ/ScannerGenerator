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
	
	private int contentOffset = 6;
	
	public Parser() {
		File inFile = new File("grammar.txt");
		
		try {
			Scanner in = new Scanner(inFile);
			
			while(in.hasNextLine()) {
				String s = in.nextLine();
				char[] chars = s.toCharArray();
				NonTerminal nt = new NonTerminal();
				int i = 0;
				while(chars[i] != '>') {
					nt.addToText(chars[i++]);
				}
				nt.addToText('>');
				nonTerminals.add(nt);
				
				String secondHalf = String.valueOf(chars).split("::=")[1];
				nt.setContents(secondHalf.split("\\|"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(NonTerminal non : nonTerminals) {
			for(String s : non.getContents())
				System.out.println(s);
		}
	}
	
	public boolean nonTerminalsContains(NonTerminal nt) {
		for(NonTerminal n : nonTerminals) {
			if(n.getText().equals(nt.getText()))
				return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		Parser p = new Parser();
	}
}
