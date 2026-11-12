package cm.klg.map.adapter.rest.inbound;

import static org.springframework.http.HttpStatus.OK;

import cm.klg.common.base.transaction.UseCaseExecutor;
import cm.klg.generated.map.adapter.rest.inbound.api.QuarterApi;
import cm.klg.generated.map.adapter.rest.inbound.dto.QuarterDTO;
import cm.klg.map.application.usecase.GetQuarterByIdUseCase;
import cm.klg.map.application.usecase.GetQuartersByDistrictUseCase;
import cm.klg.map.domain.district.DistrictId;
import cm.klg.map.domain.quarter.QuarterId;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuarterController implements QuarterApi {
  private final UseCaseExecutor useCaseExecutor;
  private final RestMapper restMapper;
  private final GetQuartersByDistrictUseCase getQuartersByDistrictUseCase;
  private final GetQuarterByIdUseCase getQuarterByIdUseCase;

  @Override
  public ResponseEntity<List<QuarterDTO>> getQuartersByDistrict(UUID districtId) {
    var result =
        useCaseExecutor.executeQuery(
            () -> getQuartersByDistrictUseCase.execute(new DistrictId(districtId)));
    return ResponseEntity.status(OK).body(result.stream().map(restMapper::toQuarterDTO).toList());
  }

  @Override
  public ResponseEntity<QuarterDTO> getQuarterById(UUID quarterId) {
    var result =
        useCaseExecutor.executeQuery(
            () -> getQuarterByIdUseCase.execute(QuarterId.from(quarterId)));
    return ResponseEntity.ok(restMapper.toQuarterDTO(result));
  }
}
