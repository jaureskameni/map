package cm.klg.map.application.usecase;

import cm.klg.map.application.outbound.CityRepository;
import cm.klg.map.application.views.CitiesView1;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllCitiesUseCase {
  private final CityRepository cityRepository;

  public List<CitiesView1> execute() {
    return cityRepository.loadAllAsViews1();
  }
}
