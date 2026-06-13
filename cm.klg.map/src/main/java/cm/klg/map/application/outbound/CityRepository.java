package cm.klg.map.application.outbound;

import cm.klg.map.application.views.CitiesView1;
import cm.klg.map.domain.city.City;
import cm.klg.map.domain.city.CityId;
import java.util.List;
import java.util.Optional;

public interface CityRepository {
  Optional<City> findById(CityId id);

  void save(City city);

  List<CitiesView1> loadAllAsViews1();
}
