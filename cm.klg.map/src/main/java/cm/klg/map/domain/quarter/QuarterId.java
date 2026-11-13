package cm.klg.map.domain.quarter;

import java.util.UUID;

public record QuarterId(UUID value) {
  public static QuarterId generate() {
    return new QuarterId(UUID.randomUUID());
  }

  public static QuarterId from(UUID value) {
    return new QuarterId(value);
  }
}
