import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ParsingTable {
	HashMap<String,ArrayList<ProductionRule>>[][] table;

	int row,col;
	List<Token> tokens;
	ArrayList<NonTerminal> nt = new ArrayList<NonTerminal>();
	Set<NonTerminal> set;
	Map<String, Token> idMap;

	public ParsingTable(int numTokens, int numNT, List<Token> tokens, Set<NonTerminal> set, Map<String, Token> idMap){
		col = numTokens;
		row = numNT;
		this.idMap = idMap;

		this.tokens = tokens;
		this.set = set;
		for(NonTerminal N : set){
			nt.add(N);
		}

		table = new HashMap[numTokens][numNT];
		for(int x = 0; x<col; x++){
			for(int y = 0; y<row; y++){
				table[x][y] = new HashMap<String,ArrayList<ProductionRule>>();
				String s = tokens.get(x).getText();
				table[x][y].put(nt.get(y).getText()+","+s,new ArrayList<ProductionRule>());
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
    public ProductionRule get(NonTerminal N, Token T) {
      if (idMap.containsKey(T.getText())) {
        T = idMap.get(T.getText());
      }
      //System.out.println(N+","+T);
      /*System.out.println(tokens.indexOf(T));
      System.out.println(nt.indexOf(N));
      System.out.println(table[tokens.indexOf(T)][nt.indexOf(N)]);
      System.out.println(table[tokens.indexOf(T)][nt.indexOf(N)].get(N+","+T).get(0));*/
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
