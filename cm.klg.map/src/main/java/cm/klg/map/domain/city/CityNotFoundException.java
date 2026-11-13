package cm.klg.map.domain.city;

import static cm.klg.map.domain.exception.MapErrorCode.MAP_404_001;

import cm.klg.common.base.exception.DomainException;

public class CityNotFoundException extends DomainException {
  public CityNotFoundException() {
    super(MAP_404_001);
  }
}
