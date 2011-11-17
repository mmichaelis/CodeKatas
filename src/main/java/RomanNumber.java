import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @since 11/10/11
 */
public class RomanNumber {
  private int number;
  private static final LinkedHashMap<Integer, String> bases = new LinkedHashMap<Integer, String>();

  static {
    bases.put(10, "X");
    bases.put(5, "V");
    bases.put(1, "I");
  }

  public RomanNumber(final int number) {
    this.number = number;
  }

  public String getRoman() {
    int currentNumber = number;
    StringBuilder sb = new StringBuilder();

    Map.Entry<Integer, String> lastEntry = null;
    for (Map.Entry<Integer, String> entry : bases.entrySet()) {
      if (lastEntry != null) {
        final int diff = lastEntry.getKey() - currentNumber;

        StringBuilder sb2 = new StringBuilder();
        if (Math.abs(currentNumber - lastEntry.getKey()) == entry.getKey()) {
          sb2.append(StringUtils.repeat(entry.getValue(), Math.abs(diff)));
          if (diff < 0) {
            sb2.insert(0, lastEntry.getValue());
          } else {
            sb2.append(lastEntry.getValue());
          }
          sb.append(sb2.toString());
        }
      }
      while (currentNumber >= entry.getKey()) {
        sb.append(entry.getValue());
        currentNumber -= entry.getKey();
      }
      lastEntry = entry;
    }

    return sb.toString();
  }
}
