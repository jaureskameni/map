package cm.klg.map.domain.district;

public record DistrictName(String value) {
  public static DistrictName from(String value) {
    return new DistrictName(value);
  }
}
