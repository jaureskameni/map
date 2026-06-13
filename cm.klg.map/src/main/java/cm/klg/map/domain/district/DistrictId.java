package cm.klg.map.domain.district;

import java.util.UUID;

public record DistrictId(UUID value) {
  public static DistrictId generate() {
    return new DistrictId(UUID.randomUUID());
  }
}
