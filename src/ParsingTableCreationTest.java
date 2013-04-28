import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;



public class ParsingTableCreationTest {
	
	public List<Token> buildNonTerminalList(){
		//create token list
		List<Token> tokens = new ArrayList<Token>();
		
		//creates a new Terminal and sets the text to the first sample grammar text
		Terminal one = new Terminal("begin");
		//adds the terminal to the token list
		tokens.add(one);
		
		Terminal two = new Terminal("end");
		tokens.add(two);
		
		Terminal three = new Terminal("replace");
		tokens.add(three);
		
		Terminal four = new Terminal("with");
		tokens.add(four);
		
		Terminal five = new Terminal("in");
		tokens.add(five);		
		
		Terminal six = new Terminal(";");
		tokens.add(six);
		
		Terminal seven = new Terminal("recursivereplace");
		tokens.add(seven);
		
		Terminal eight = new Terminal("=");
		tokens.add(eight);
		
		Terminal nine = new Terminal("#");
		tokens.add(nine);
		
		Terminal ten = new Terminal("maxfreqstring");
		tokens.add(ten);
		
		Terminal eleven = new Terminal("(");
		tokens.add(eleven);
		
		Terminal twelve = new Terminal(")");
		tokens.add(twelve);
		
		Terminal thirteen = new Terminal(">!");
		tokens.add(thirteen);
		
		Terminal fifteen = new Terminal(",");
		tokens.add(fifteen);
		
		Terminal sixteen = new Terminal("find");
		tokens.add(sixteen);
		
		Terminal seventeen = new Terminal("diff");
		tokens.add(seventeen);
		
		Terminal eightteen = new Terminal("union");
		tokens.add(eightteen);
		
		Terminal nineteen = new Terminal("inters");
		tokens.add(nineteen);
		
		Terminal twenty = new Terminal("print");
		tokens.add(twenty);
		
		//adds the identifiers to the token list from the grammar text
		Identifier a = new Identifier("REGEX");
		tokens.add(a);
		
		Identifier b = new Identifier("ASCII-STR");
		tokens.add(b);
		
		Identifier c = new Identifier("ID");
		tokens.add(c);
		
		return tokens;
	}
	
	public Set<NonTerminal> buildNonTerminalSet(){
		Set<NonTerminal> nt = new HashSet<NonTerminal>();
		
		//makes the new NonTerminal sets its text and adds its rules
		NonTerminal one = new NonTerminal("<MiniRE-program>");
		String[] st = new String[]{"begin","<statement-list>","end"};
		Rule r = new Rule(st);
		one.addRule(r);
		nt.add(one);
		
		NonTerminal two = new NonTerminal("<statement-list>");
		String[] st2 = new String[]{"<statement>","<statement-list-tail>"};
		Rule r2 = new Rule(st2);
		two.addRule(r2);
		nt.add(two);
		
		NonTerminal three = new NonTerminal("<statement-list-tail>");
		String[] st3 = new String[]{"replace", "REGEX", "with", "ASCII-STR", "in", "<file-names>"};
		String[] st3b = new String[]{"<epsilon>"};	
		Rule r3 = new Rule(st3);
		Rule r3b = new Rule(st3b);
		three.addRule(r3);
		three.addRule(r3b);
		nt.add(three);
		
		NonTerminal four = new NonTerminal("<statement>");
		String[] st4 = new String[]{"replace", "REGEX", "with", "ASCII-STR", "in", "<file-names>"};
		String[] st4b = new String[]{"recursivereplace", "REGEX", "with", "ASCII-STR", "in", "<file-names>", ";"};		
		String[] st4c = new String[]{"ID", "=", "<statement-righthand>", ";"};
		String[] st4d = new String[]{"print","(", "<exp-list>",")",";"};		
		Rule r4 = new Rule(st4);
		Rule r4b = new Rule(st4b);
		Rule r4c = new Rule(st4c);
		Rule r4d = new Rule(st4d);
		four.addRule(r4);
		four.addRule(r4b);
		four.addRule(r4c);
		four.addRule(r4d);
		nt.add(four);
		
		NonTerminal five = new NonTerminal("<statement-righthand>");
		String[] st5 = new String[]{"<exp>"};
		String[] st5b = new String[]{"#", "<exp>"};
		String[] st5c = new String[]{"maxfreqstring","(ID)"};
		Rule r5 = new Rule(st5);
		Rule r5b = new Rule(st5b);
		Rule r5c = new Rule(st5c);
		five.addRule(r5);
		five.addRule(r5b);
		five.addRule(r5c);
		nt.add(five);
		
		NonTerminal six = new NonTerminal("<file-names>");
		String[] st6 = new String[]{"<source-file>",">!","<destination-file>"};
		Rule r6 = new Rule(st6);
		six.addRule(r6);
		nt.add(six);
		
		NonTerminal seven = new NonTerminal("<source-file>");
		String[] st7 = new String[]{"ASCII-STR"};
		Rule r7 = new Rule(st7);
		seven.addRule(r7);
		nt.add(seven);
		
		NonTerminal eight = new NonTerminal("<destination-file>");
		String[] st8 = new String[]{"ASCII-STR"};
		Rule r8 = new Rule(st8);
		eight.addRule(r8);
		nt.add(eight);
		
		NonTerminal nine = new NonTerminal("<exp-list>");
		String[] st9 = new String[]{"<exp>","<exp-list-tail>"};
		Rule r9 = new Rule(st9);
		nine.addRule(r9);
		nt.add(nine);
		
		NonTerminal ten = new NonTerminal("<exp-list-tail>");
		String[] st10 = new String[]{",","<exp>","<exp-list-tail>"};
		String[] st10b = new String[]{"<epsilon>"};
		Rule r10 = new Rule(st10);
		Rule r10b = new Rule(st10b);
		ten.addRule(r10);
		ten.addRule(r10b);
		nt.add(ten);
		
		NonTerminal eleven = new NonTerminal("<exp>");
		String[] st11 = new String[]{"ID"};
		String[] st11b = new String[]{"(","<exp>",")"};
		String[] st11c = new String[]{"<term>","<exp-tail>"};
		Rule r11 = new Rule(st11);
		Rule r11b = new Rule(st11b);
		Rule r11c = new Rule(st11c);
		eleven.addRule(r11);
		eleven.addRule(r11b);
		eleven.addRule(r11c);
		nt.add(eleven);
		
		NonTerminal el = new NonTerminal("<exp>");
		String[] stel = new String[]{"<bin-op> <term>","<exp-tail>"};
		String[] stelb = new String[]{"<epsilon>"};
		Rule rel = new Rule(stel);
		Rule relb = new Rule(stelb);
		eleven.addRule(rel);
		eleven.addRule(relb);
		nt.add(el);
		
		NonTerminal twelve = new NonTerminal("<term>");
		String[] st12 = new String[]{"find","REGEX","in","<file-name>"};
		Rule r12 = new Rule(st12);
		twelve.addRule(r12);
		nt.add(twelve);
		
		NonTerminal tw = new NonTerminal("<file-name>");
		String[] sttw = new String[]{"ASCII-STR"};
		Rule rtw = new Rule(sttw);
		twelve.addRule(rtw);
		nt.add(tw);
		
		NonTerminal thirteen = new NonTerminal("<bin-op>");
		String[] st13 = new String[]{"diff"};
		String[] st13b = new String[]{"union"};
		String[] st13c = new String[]{"inters"};
		Rule r13 = new Rule(st13);
		Rule r13b = new Rule(st13b);
		Rule r13c = new Rule(st13c);
		thirteen.addRule(r13);
		thirteen.addRule(r13b);
		thirteen.addRule(r13c);
		nt.add(thirteen);		
		
		return nt;
	}
	
	public List<ProductionRule> buildProductionRuleList(Set<NonTerminal> nt){
		List<ProductionRule> productionRules = new ArrayList<ProductionRule>();
		
		//goes through all nonterminals from the list and makes a ProductionRule based on the
		//NonTerminal rules and links the NonTermianl
		for(NonTerminal t : nt){
			for(Rule r : t.getRules()){
				ProductionRule p = new ProductionRule(t,r.getRule());
				productionRules.add(p);
			}
		}
		
		
		return productionRules;
	}
	
	@Test
	public void numberOfStatesShouldBeEqual() {
		Set<NonTerminal> nonTerminals = buildNonTerminalSet();//makes the Nonterminal List an populates
		List<Token> tokens = buildNonTerminalList();//token list created and populates
		List<ProductionRule> productionRules = buildProductionRuleList(nonTerminals);//production takes in the nonterminal list
		
		Parser myParser = new Parser(nonTerminals,tokens,productionRules);//creates new parser from hard coded data		
		Parser p = new Parser(); //makes the parser from the grammar text file based on the parser code
		
		/*
		for(Token t : myParser.tokens)
			System.out.print(t.getText()+" ");
		System.out.print("\n");
		for(Token t : p.tokens)
			System.out.print(t.getText()+" ");
		*/
		
		/*for(ProductionRule pr : myParser.productionRules)
			System.out.print(pr.getN().getText() + " ");
		System.out.print("\n");
		for(ProductionRule pr : p.productionRules)
			System.out.print(pr.getN().getText() + " ");*/
		
		
		ParsingTable myTable = p.buildTable();//makes an LL1 table
		List<NonTerminal> nt = new ArrayList<NonTerminal>(myParser.nonTerminals);
		//myTable.addEntry(nt.get(0), myParser.tokens.get(0), myParser.productionRules.get(0));//adds entry to the table just to see if it worked
		myTable.printTable();//prints the table in a table format
		
		assertEquals(p.nonTerminals.size(),myParser.nonTerminals.size());//checks if the size of both parsers nonterminal lists
		assertEquals(p.productionRules.size(),myParser.productionRules.size());//same with token list
		//assertEquals(p.tokens.size(),myParser.tokens.size());
	}

}
