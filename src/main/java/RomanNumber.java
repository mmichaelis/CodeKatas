import org.apache.commons.lang.StringUtils;

/**
 * @since 11/10/11
 */
public class RomanNumber {
  private int number;

  public RomanNumber(final int number) {
    this.number = number;
  }

  public String getRoman() {
    if (number >= 9) {
      return makeRoman(10, "X");
    }
    if (number >= 4) {
      return makeRoman(5, "V");
    }
    return StringUtils.repeat("I", number);
  }

  private String makeRoman(final int base, final String romanBase) {
    final int diff = base - number;
    StringBuilder sb = new StringBuilder();
    sb.append(StringUtils.repeat("I", Math.abs(diff)));
    if (diff < 0) {
      sb.insert(0, romanBase);
    } else {
      sb.append(romanBase);
    }
    return sb.toString();
  }
}
