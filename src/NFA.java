import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;


/**
 * Heavily unfinished
 * @author jarv
 *
 */
public class NFA {
  private NFAState start;

  /**
   * Creates a primitive nfa based on a char class
   * @param chars the accepted characters from a char class
   */
  public NFA(Set<Character> chars) {
    NFAState acceptState = NFAState.builder()
        .setAccept(true)
        .setTransition(chars)
        .build();
    this.start = NFAState.builder()
        .setTransition(null) // empty transition.
        .addNextState(acceptState)
        .build();
  }

  public NFA() {
    // TODO Auto-generated constructor stub
  }

  /**
   * Returns a list of accept states in a given NFA.
   */
  public List<NFAState> findAcceptStates() {
  	List<NFAState> acceptStates = new ArrayList<NFAState>();

  	Set<NFAState> discovered = new HashSet<NFAState>();
  	Stack<NFAState> nextStatesToExplore = new Stack<NFAState>();
  	NFAState temp;

  	nextStatesToExplore.push(getStartState());

  	while(!nextStatesToExplore.empty()) {
  		temp = nextStatesToExplore.pop();

  		if(temp.isAccept()) {
  			acceptStates.add(temp);
  		}

  		for(NFAState nextState : temp.getNextStates()) {
  			if((!nextStatesToExplore.contains(nextState))
  				&& (!discovered.contains(nextState))) {
  				nextStatesToExplore.push(nextState);
  			}
  		}

  		discovered.add(temp);
  	}

  	return acceptStates;
  }

  public NFA copy() {
    NFA copy = new NFA();
    copy.setStartState(copyState(start));
    return copy;
  }
  public NFAState copyState(NFAState s) {
    if (s.getNextStates().size() > 0) {
      List<NFAState> copiedNext = new ArrayList<NFAState>();
      for (NFAState old : s.getNextStates()) {
        copiedNext.add(copyState(old));
      }
      return NFAState.builder()
          .setAccept(s.isAccept())
          .setNextStates(copiedNext)
          .setTransition(s.getTransition())
          .build();
    }
    return s;

  }
  public boolean accepts(String str) {
      return start.accepts(str);
  }

  public NFAState getStartState() {
  	return start;
  }

  public NFA setStartState(NFAState start) {
  	this.start = start;
    return this;
  }
}
