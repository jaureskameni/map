package cm.klg.map.adapter.rest.inbound;

import cm.klg.common.base.exception.HttpErrorException;
import cm.klg.common.base.exception.InternalException;
import cm.klg.common.base.exception.ResourceNotFoundException;
import cm.klg.common.base.transaction.DomainToHttpExceptionTranslator;
import cm.klg.map.domain.city.CityNotFoundException;
import cm.klg.map.domain.district.DistrictNotFoundException;
import cm.klg.map.domain.quarter.QuarterNotFoundException;
import java.util.Optional;

public record DefaultDomainToHttpExceptionTranslator() implements DomainToHttpExceptionTranslator {
  @Override
  public HttpErrorException translate(RuntimeException ex) {
    var message = Optional.ofNullable(ex.getMessage()).orElse("missing error code");
    return switch (ex) {
      case CityNotFoundException _, DistrictNotFoundException _, QuarterNotFoundException _ ->
          new ResourceNotFoundException(message);
      default -> new InternalException(message, ex);
    };
  }
}
