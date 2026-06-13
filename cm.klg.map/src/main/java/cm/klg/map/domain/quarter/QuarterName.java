package cm.klg.map.domain.quarter;

public record QuarterName(String value) {
  public static QuarterName from(String value) {
    return new QuarterName(value);
  }
}
