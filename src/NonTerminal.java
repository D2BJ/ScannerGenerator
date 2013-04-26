import java.util.ArrayList;
import java.util.List;


public class NonTerminal {
	
	String text = "";
	String[] contents;

	List<NonTerminal> firstSet = new ArrayList<NonTerminal>();
	List<NonTerminal> followSet = new ArrayList<NonTerminal>();
	
	public NonTerminal() {}
	
	public NonTerminal(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String toString() {
		return getText();
	}
	
	public String[] getContents() {
		return contents;
	}

	public void setContents(String[] contents) {
		this.contents = contents;
	}
	
	public void addToText(char c) {
		text += String.valueOf(c);
	}
}
