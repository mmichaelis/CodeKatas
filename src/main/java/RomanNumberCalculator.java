/**
 * Utitlity class that can calculate the roman representation of an arabic number using the 'Substraktionsregel'.
 */
public class RomanNumberCalculator {

  /**
   * Represents a Roman Digit (I, V, X, ...).
   * Knows the arabic equal and if it is a power of ten.
   */
  private enum RomanDigit {
    THOUSAND(1000, "M"),
    FIVEHUNDRED(500, "D"),
    ONEHUNDRED(100, "C"),
    FIFTY(50, "L"),
    TEN(10, "X"),
    FIVE(5, "V"),
    ONE(1, "I"),
    NULL(0, "0");

    final int arabic;
    final String roman;
    private RomanDigit subtrahend = null;

    RomanDigit(int arabic, String roman) {
      this.arabic = arabic;
      this.roman = roman;
    }

    public RomanDigit getSubtrahend() {
      return subtrahend;
    }

    private void setSubtrahend(RomanDigit subtrahend) {
      this.subtrahend = subtrahend;
    }
  }

  static {
    calculateSubtrahends();
  }

  /**
   * Calculate the roman representation of an arabic number.
   *
   * @param number an arabic number
   * @return the roman representation
   */
  public static String getRoman(int number) {
    StringBuilder sb = new StringBuilder();

    // iterate over all roman digits (sorted by value, descending)
    for (int i = 0; i < RomanDigit.values().length - 1; i++) {
      RomanDigit romanDigit = RomanDigit.values()[i];

      // while 'number' is greater or equal than the current digit,
      // substract the digit and append its roman value to the result
      while (number >= romanDigit.arabic) {
        sb.append(romanDigit.roman);
        number -= romanDigit.arabic;
      }

      // 'Substraktionsregel': x = s.r (e.g IV, IX but not VX)
      // if the current roman digit 'r' does not fit in, test if the 'Substraktionsregel' produces a number 'x' that fits.
      // to build 'x' the subtrahend 's' is the pre-predecessor in case 'r' is a power of ten else 's' is the predecessor.
      if (number >= romanDigit.arabic - romanDigit.getSubtrahend().arabic) {
        sb.append(romanDigit.getSubtrahend().roman).append(romanDigit.roman);
        number -= romanDigit.arabic - romanDigit.getSubtrahend().arabic;
      }
    }

    return sb.toString();
  }

  /**
   * Calculate the arabic number represented by the roman number.
   *
   * @param roman a roman number
   * @return the arabic number represented by the roman number
   */
  public static int getArabic(String roman) {
    int result = 0;

    // iterate over all roman digits (sorted by value, descending)
    for (int i = 0; i < RomanDigit.values().length - 1; i++) {
      RomanDigit romanDigit = RomanDigit.values()[i];

      // while 'roman' starts with the current roman digit remove it from 'roman' and add its value to 'result'
      while (roman.startsWith(romanDigit.roman)) {
        result += romanDigit.arabic;
        roman = roman.substring(1);
      }

      // test if the 'Substraktionsregel' can be applied
      String x = romanDigit.getSubtrahend().roman + romanDigit.roman;
      if (roman.startsWith(x)) {
        result += romanDigit.arabic - romanDigit.getSubtrahend().arabic;
        roman = roman.substring(2);
      }

    }
    return result;

  }

  /**
   * Calculates the 'subtrahend' according to the 'Substraktionsregel' for every RomanDigit.
   */
  private static void calculateSubtrahends() {
    RomanDigit[] romanDigits = RomanDigit.values();

    for (int i = 0; i < romanDigits.length; i++) {
      RomanDigit subtrahend;

      // calculate if power of ten
      // x is a power of ten iff log10(x) = y with y being a natural number
      boolean isPowerOfTen = false;
      double log10 = Math.log10(romanDigits[i].arabic);
      if (Double.compare(0.0, log10 - Math.floor(log10)) == 0) {
        isPowerOfTen = true;
      }

      // 'Substraktionsregel': x = s.r (e.g IV, IX but not VX)
      // if the current roman digit 'r' does not fit in, test if the 'Substraktionsregel' produces a number 'x' that fits.
      // to build 'x' the subtrahend 's' is the pre-predecessor in case 'r' is a power of ten else 's' is the predecessor.
      if (isPowerOfTen) {
        if (i >= romanDigits.length - 2) continue;
        subtrahend = romanDigits[i + 2];
      } else {
        if (i >= romanDigits.length - 1) continue;
        subtrahend = romanDigits[i + 1];
      }

      romanDigits[i].setSubtrahend(subtrahend);
    }

    RomanDigit.ONE.setSubtrahend(RomanDigit.NULL);
    RomanDigit.NULL.setSubtrahend(RomanDigit.NULL);
  }


}