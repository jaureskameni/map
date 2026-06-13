package cm.klg.map.application.outbound;

import cm.klg.map.application.views.DistrictsView1;
import cm.klg.map.domain.city.CityId;
import java.util.List;

public interface DistrictRepository {
  List<DistrictsView1> loadByCityIdAsViews1(CityId cityId);
}
