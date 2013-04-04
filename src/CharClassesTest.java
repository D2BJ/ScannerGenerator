
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class CharClassesTest {

  @Test
  public void naiveCharClassesShouldProduceValidMap() throws BadSpecException {
    CharClasses classes = new CharClasses("$DIGIT [0-2]\n$ALPHA [a-cA-B]");
    Set<Character> expectedDigits = new HashSet<Character>(Arrays.asList(new Character[]{'0','1','2'}));
    Set<Character> expectedAlpha = new HashSet<Character>(Arrays.asList(new Character[]{'a','b','c', 'A', 'B'}));
    assertEquals(classes.get("$DIGIT"), expectedDigits);
    assertEquals(classes.get("$ALPHA"), expectedAlpha);
  }

  @Test
  public void charClassesShouldHandleEmptyClass() throws BadSpecException {
    new CharClasses("EMPTY []");
  }

  @Test
  public void shouldNotAcceptInvalidRanges() {
    try {
      new CharClasses("$A [9-9]");
      fail();
    } catch (BadSpecException e) {
      System.out.println("Caught A BadSpecException");
    }
    try {
      new CharClasses("$A [B-A]");
      fail();
    } catch (BadSpecException e) {
      // Shouldn't fail
    }
  }

  @Test
  public void shouldNotAcceptMissingInOperator() {
    try {
      new CharClasses("$DIGIT [0-9]\n$A [^0-5] EN $DIGIT");
    } catch (BadSpecException e) {
      System.out.println("Caught A BadSpecException");
    }
  }

}
