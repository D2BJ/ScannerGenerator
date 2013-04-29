/**
 * We assume that any symbol that is not a nonterminal is a token.
 * This includes terminals and identifiers.
 *
 * @author dgreenhalgh
 */
public class Token extends Symbol{

	public Token() {}

	public Token(String text) {
		super(text);
	}

	@Override
  public boolean equals(Object o) {
	  Token s = (Token)o;
	  return s.getText().equals(getText());
	}
	@Override
  public String toString() {
		return super.toString();
	}
}
