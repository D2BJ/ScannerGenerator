/**
 * A class to hold operations performed on NFAs including concat, union, star, and plus.
 */
public class NFAOperations {

	/**
	 * Concatenate two NFAs together.
	 * Ex. ($DIGIT $LOWER)
	 */ 
	public static NFA concat(NFA nfa_a, NFA nfa_b) {
	  	// set accept states isAccept to false 
	  	// and set next states to start state of b
	  	List<NFAState> acceptStates = nfa_a.findAcceptStates();

	  	for(NFAState acceptState : acceptStates) {
	  		acceptState.addNext(nfa_b.getStartState());
	  		acceptState.setAccept(false);
	  	}
	  	// set accept state of a next to start state of b

	  	return a;
  	}

  	/**
  	 * Unionize two NFAs.
  	 * Ex. ($DIGIT | $LOWER)
  	 */
  	public static NFA union(NFA nfa_a, NFA nfa_b) {
  		Set<Character> newSet = new Set<Character>(nfa_a.getChars());
  		newSet.addAll(nfa_b.getChars());

  		//create new nfa to hold unioned nfas
  		NFA newNFA = new NFA(newSet);
  		List<NFAState> nextStates = Arrays.asList(nfa_a.getStartState(), nfa_b.getStartState());

  		//set transition into a and b to null for empty string
  		nfa_a.getStartState().setTransition(null);
  		nfa_b.getStartState().setTransition(null);
  		newNFA.setStartState(new NFAState(false, new Set<Character>(), nextStates));

  		//create final state
  		NFAState finalState = new NFAState(true, null, null);
  		List<NFAState> acceptStates = nfa_a.findAcceptStates();
  		acceptStates.addAll(nfa_b.findAcceptStates);

  		//point a and b accept states to finalstate
  		for(NFAState acceptState : acceptStates) {
	  		acceptState.addNext(finalState);
	  		acceptState.setAccept(false);
	  	}

	  	return newNFA;
  	}

  	/**
  	 * Star indicates that an NFA should be repeated 0 or more times.
  	 * Ex. ($DIGIT*)
  	 */
  	public static NFA star(NFA nfa_a) {
  		List<NFAState> acceptStates = nfa_a.findAcceptStates();

  		for(NFAState acceptState : acceptStates) {
  			acceptState.addNext(nfa_a.getStartState());
  			acceptState.setAccept(false);
  		}

  		nfa_a.getStartState().setAccept(true);
  		nfa_a.getStartState().setTransition(null);
  	}

  	/**
  	 * Plus indicates that an NFA should be repeated 1 or more times.
  	 * Ex. ($DIGIT+)
  	 */
  	public static NFA plus(NFA nfa_a) {
  		return union(nfa_a, star(nfa_a));
  	}

}