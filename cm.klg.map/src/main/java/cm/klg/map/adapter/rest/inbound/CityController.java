package cm.klg.map.adapter.rest.inbound;

import static org.springframework.http.HttpStatus.OK;

import cm.klg.common.base.transaction.UseCaseExecutor;
import cm.klg.generated.map.adapter.rest.inbound.api.CityApi;
import cm.klg.generated.map.adapter.rest.inbound.dto.CityDTO;
import cm.klg.map.application.usecase.GetAllCitiesUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CityController implements CityApi {
  private final UseCaseExecutor useCaseExecutor;
  private final RestMapper restMapper;
  private final GetAllCitiesUseCase getAllCitiesUseCase;

  @Override
  public ResponseEntity<List<CityDTO>> getAllCities() {
    var result = useCaseExecutor.executeQuery(getAllCitiesUseCase::execute);
    return ResponseEntity.status(OK).body(result.stream().map(restMapper::toCityDTO).toList());
  }
}
