package cm.klg.map.domain.district;

import static cm.klg.map.domain.exception.MapErrorCode.MAP_404_002;

import cm.klg.common.base.exception.DomainException;

public class DistrictNotFoundException extends DomainException {
  public DistrictNotFoundException() {
    super(MAP_404_002);
  }
}
