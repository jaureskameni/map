package cm.klg.map.application.usecase;

import cm.klg.map.application.outbound.DistrictRepository;
import cm.klg.map.application.views.DistrictsView1;
import cm.klg.map.domain.city.CityId;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetDistrictsByCityUseCase {
  private final DistrictRepository districtRepository;

  public List<DistrictsView1> execute(CityId cityId) {
    return districtRepository.loadByCityIdAsViews1(cityId);
  }
}
