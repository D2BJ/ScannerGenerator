import java.util.ArrayList;
import java.util.List;


public class NonTerminal {
	
	String text = "";
	
	List<NonTerminal> firstSet = new ArrayList<NonTerminal>();
	List<NonTerminal> followSet = new ArrayList<NonTerminal>();
	
	public NonTerminal(String text) {
		this.text = text;
	}

}
