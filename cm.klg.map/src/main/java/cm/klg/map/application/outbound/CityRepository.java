package cm.klg.map.application.outbound;

import cm.klg.map.application.views.CitiesView1;
import cm.klg.map.domain.city.City;
import cm.klg.map.domain.city.CityId;
import java.util.List;

public interface CityRepository {
  List<CitiesView1> loadAllAsViews1();

  City load(CityId cityId);
}
