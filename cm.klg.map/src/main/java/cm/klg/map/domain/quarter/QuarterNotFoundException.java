package cm.klg.map.domain.quarter;

import static cm.klg.map.domain.exception.MapErrorCode.MAP_404_003;

import cm.klg.common.base.exception.DomainException;

public class QuarterNotFoundException extends DomainException {
  public QuarterNotFoundException() {
    super(MAP_404_003);
  }
}
