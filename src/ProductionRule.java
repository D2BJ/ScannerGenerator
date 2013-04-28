import java.util.ArrayList;
import java.util.List;


public class ProductionRule {
	NonTerminal N;
	List<Symbol> rule;
	
	public ProductionRule(NonTerminal N, List<Symbol> rule){
		this.N = N;
		this.rule = rule;
	}

	public NonTerminal getN() {
		return N;
	}

	public void setN(NonTerminal n) {
		N = n;
	}

	public List<Symbol> getRule() {
		return rule;
	}

	public void setRule(ArrayList<Symbol> rule) {
		this.rule = rule;
	}
	public String toString(){
		return(N.getText() + "-->" + rule.toString());
	}

}
