package cm.klg.map.domain.exception;

import cm.klg.common.base.utils.ErrorCode;
import lombok.Getter;

public enum MapErrorCode implements ErrorCode {
  MAP_404_001("MAP_404_001", "City Not Found"),
  MAP_404_002("MAP_404_002", "District Not Found"),
  MAP_404_003("MAP_404_003", "Quarter Not Found");

  private final String value;
  @Getter private final String description;

  MapErrorCode(String value, String description) {
    this.value = value;
    this.description = description;
  }

  @Override
  public String value() {
    return value;
  }
}
