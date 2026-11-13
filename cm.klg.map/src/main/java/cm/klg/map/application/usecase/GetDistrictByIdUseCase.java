package cm.klg.map.application.usecase;

import cm.klg.map.application.outbound.DistrictRepository;
import cm.klg.map.domain.district.District;
import cm.klg.map.domain.district.DistrictId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetDistrictByIdUseCase {
  private final DistrictRepository districtRepository;

  public District execute(DistrictId districtId) {
    return districtRepository.load(districtId);
  }
}
