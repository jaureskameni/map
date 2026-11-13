package cm.klg.map.adapter.rest.inbound;

import static org.springframework.http.HttpStatus.OK;

import cm.klg.common.base.transaction.UseCaseExecutor;
import cm.klg.generated.map.adapter.rest.inbound.api.DistrictApi;
import cm.klg.generated.map.adapter.rest.inbound.dto.DistrictDTO;
import cm.klg.map.application.usecase.GetDistrictByIdUseCase;
import cm.klg.map.application.usecase.GetDistrictsByCityUseCase;
import cm.klg.map.domain.city.CityId;
import cm.klg.map.domain.district.DistrictId;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DistrictController implements DistrictApi {
  private final UseCaseExecutor useCaseExecutor;
  private final RestMapper restMapper;
  private final GetDistrictsByCityUseCase getDistrictsByCityUseCase;
  private final GetDistrictByIdUseCase getDistrictByIdUseCase;

  @Override
  public ResponseEntity<List<DistrictDTO>> getDistrictsByCity(UUID cityId) {
    var result =
        useCaseExecutor.executeQuery(() -> getDistrictsByCityUseCase.execute(new CityId(cityId)));
    return ResponseEntity.status(OK).body(result.stream().map(restMapper::toDistrictDTO).toList());
  }

  @Override
  public ResponseEntity<DistrictDTO> getDistrictById(UUID districtId) {
    var result =
        useCaseExecutor.executeQuery(
            () -> getDistrictByIdUseCase.execute(DistrictId.from(districtId)));
    return ResponseEntity.ok(restMapper.toDistrictDTO(result));
  }
}
