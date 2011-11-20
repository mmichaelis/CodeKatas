import java.util.Arrays;
import java.util.Comparator;

/**
 * @since 11/10/11
 */
public class RomanNumberCalculator {
  private enum RomanDigit {
    THOUSAND(1000, "M", true),
    FIVEHUNDRED(500, "D"),
    ONEHUNDRED(100, "C", true),
    FIFTY(50, "L"),
    TEN(10, "X", true),
    FIVE(5, "V"),
    ONE(1, "I");

    private final int arabic;
    private final String roman;
    private final boolean isPowerOfTen;

    RomanDigit(int arabic, String roman) {
      this(arabic, roman, false);
    }

    RomanDigit(int arabic, String roman, boolean isPowerOfTen) {
      this.arabic = arabic;
      this.roman = roman;
      this.isPowerOfTen = isPowerOfTen;
    }
  };

  public static String getRoman(int number) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < RomanDigit.values().length; i++) {
      while (number >= RomanDigit.values()[i].arabic) {
        sb.append(RomanDigit.values()[i].roman);
        number -= RomanDigit.values()[i].arabic;
      }

      RomanDigit subtrahend;
      if (RomanDigit.values()[i].isPowerOfTen) {
        if (i >= RomanDigit.values().length-2) continue;
        subtrahend = RomanDigit.values()[i+2];
      } else {
        if (i >= RomanDigit.values().length-1) continue;
        subtrahend = RomanDigit.values()[i+1];
      }

      if (number >= RomanDigit.values()[i].arabic - subtrahend.arabic) {
        sb.append(subtrahend.roman).append(RomanDigit.values()[i].roman);
        number -= RomanDigit.values()[i].arabic - subtrahend.arabic;
      }
    }

    return sb.toString();
  }

}
