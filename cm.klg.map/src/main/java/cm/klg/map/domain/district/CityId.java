package cm.klg.map.domain.district;

import java.util.UUID;

public record CityId(UUID value) {
  public static CityId generate() {
    return new CityId(UUID.randomUUID());
  }

  public static CityId from(UUID value) {
    return new CityId(value);
  }
}
