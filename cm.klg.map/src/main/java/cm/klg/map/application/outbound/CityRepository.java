package cm.klg.map.application.outbound;

import cm.klg.map.application.views.CitiesView1;
import java.util.List;

public interface CityRepository {
  List<CitiesView1> loadAllAsViews1();
}
