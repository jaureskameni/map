package cm.klg.map.domain.city;

public record CityName(String value) {
  public static CityName from(String value) {
    return new CityName(value);
  }
}
