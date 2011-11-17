import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RomanNumberTest {
  private final int number;
  private final String expected;

  public RomanNumberTest(final int number, final String expected) {
    this.number = number;
    this.expected = expected;
  }

  @Test
  public void testGetRoman() throws Exception {
    final RomanNumber roman = new RomanNumber(number);
    assertEquals(expected, roman.getRoman());
  }

  @Parameterized.Parameters
  public static List<Object[]> data() {
    final Object[][] objects = {
            {1, "I"},
            {3, "III"},
            {4, "IV"},
            {5, "V"},
            {6, "VI"},
            {8, "VIII"},
            {9, "IX"},
            {10, "X"},
            {11, "XI"},
            {14, "XIV"},
            {15, "XV"},
            {16, "XVI"},
            {19, "XIX"},
            {20, "XX"},
            {21, "XXI"},
            {24, "XXIV"},
            {25, "XXV"},
            {26, "XXVI"},
    };
    return asList(objects);
  }

}
