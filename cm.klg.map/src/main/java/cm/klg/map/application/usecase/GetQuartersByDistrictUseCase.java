package cm.klg.map.application.usecase;

import cm.klg.map.application.outbound.QuarterRepository;
import cm.klg.map.application.views.QuartersView1;
import cm.klg.map.domain.district.DistrictId;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetQuartersByDistrictUseCase {
  private final QuarterRepository quarterRepository;

  public List<QuartersView1> execute(DistrictId districtId) {
    return quarterRepository.loadByDistrictIdAsViews1(districtId);
  }
}
