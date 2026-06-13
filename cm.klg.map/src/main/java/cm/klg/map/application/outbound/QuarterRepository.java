package cm.klg.map.application.outbound;

import cm.klg.map.application.views.QuartersView1;
import cm.klg.map.domain.district.DistrictId;
import cm.klg.map.domain.quarter.Quarter;
import cm.klg.map.domain.quarter.QuarterId;
import java.util.List;
import java.util.Optional;

public interface QuarterRepository {
  Optional<Quarter> findById(QuarterId id);

  void save(Quarter quarter);

  List<QuartersView1> loadByDistrictIdAsViews1(DistrictId districtId);
}
