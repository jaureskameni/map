package cm.klg.map.adapter.rest.inbound;

import static org.springframework.http.HttpStatus.OK;

import cm.klg.common.base.transaction.UseCaseExecutor;
import cm.klg.generated.map.adapter.rest.inbound.api.CityApi;
import cm.klg.generated.map.adapter.rest.inbound.dto.CityDTO;
import cm.klg.map.application.usecase.GetAllCitiesUseCase;
import cm.klg.map.application.usecase.GetCityByIdUseCase;
import cm.klg.map.domain.city.CityId;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CityController implements CityApi {
  private final UseCaseExecutor useCaseExecutor;
  private final RestMapper restMapper;
  private final GetAllCitiesUseCase getAllCitiesUseCase;
  private final GetCityByIdUseCase getCityByIdUseCase;

  @Override
  public ResponseEntity<List<CityDTO>> getAllCities() {
    var result = useCaseExecutor.executeQuery(getAllCitiesUseCase::execute);
    return ResponseEntity.status(OK).body(result.stream().map(restMapper::toCityDTO).toList());
  }

  @Override
  public ResponseEntity<CityDTO> getCityById(UUID cityId) {
    var result =
        useCaseExecutor.executeQuery(() -> getCityByIdUseCase.execute(CityId.from(cityId)));
    return ResponseEntity.ok(restMapper.toCityDTO(result));
  }
}
