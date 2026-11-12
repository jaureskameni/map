package cm.klg.map.domain.district;

import java.util.UUID;

public record DistrictId(UUID value) {
  public static DistrictId generate() {
    return new DistrictId(UUID.randomUUID());
  }

  public static DistrictId from(UUID value) {
    return new DistrictId(value);
  }
}
