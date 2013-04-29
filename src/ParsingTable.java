import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class ParsingTable {
	HashMap<String,ArrayList<ProductionRule>>[][] table;
	
	int row,col;
	List<Token> tokens;
	ArrayList<NonTerminal> nt = new ArrayList<NonTerminal>();
	Set<NonTerminal>set;
	
	public ParsingTable(int numTokens, int numNT, List<Token> tokens, Set<NonTerminal> set){
		col = numTokens;
		row = numNT;
		
		this.tokens = tokens;
		this.set = set;
		for(NonTerminal N : set){
			nt.add(N);
		}
		
		table = (HashMap<String,ArrayList<ProductionRule>>[][]) new HashMap[numTokens][numNT];
		for(int x = 0; x<col; x++){
			for(int y = 0; y<row; y++){
				table[x][y] = new HashMap<String,ArrayList<ProductionRule>>();
				table[x][y].put(nt.get(y).getText()+","+tokens.get(x).getText(),new ArrayList<ProductionRule>());
			}
		}
	}
	public void addEntry(NonTerminal nt, Token t, ProductionRule r){
		String cord = nt.getText()+","+t.getText();
		for(int x = 0; x<col; x++){
			for(int y = 0; y<row; y++){
				if(table[x][y].containsKey(cord))
					table[x][y].get(cord).add(r);
			}
		}
	}
	public HashMap<String, ArrayList<ProductionRule>>[][] getTable() {
		return table;
	}
    public ProductionRule getEntry(NonTerminal N, Token T) {
        return table[tokens.indexOf(T)][nt.indexOf(N)].get(N+","+T).get(0);
    }
    
    public void printTable()
    {
        String nontermHeader,terminalHeader,rowRule;
        for(int y=0;y<col;y++)
        {
           terminalHeader =table[y][0].keySet().toString().substring(table[y][0].keySet().toString().indexOf(",")+1,table[y][0].keySet().toString().length()-1);
           System.out.print(" "+terminalHeader);

        }
        System.out.println();
        for(int x=0;x<row;x++)
        {
            nontermHeader = table[0][x].keySet().toString().substring(1,table[0][x].keySet().toString().indexOf(","));
            System.out.print(nontermHeader+"  ");
            
            for(int y=0;y<col;y++)
            {
                rowRule = table[y][x].get(table[y][x].keySet().toArray()[0]).toString();
                System.out.print(rowRule+"  ");
                
            }
            System.out.println();
        }
    }

}
