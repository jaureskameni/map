package cm.klg.map.adapter.persistence.outbound.jpa;

import static cm.klg.map.utils.Constants.CacheKey.CITIES;

import cm.klg.map.application.outbound.CityRepository;
import cm.klg.map.application.views.CitiesView1;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

@RequiredArgsConstructor
public class CityJpaRepository implements CityRepository {
  private final CitySpringRepository citySpringRepository;
  private final JpaMapper jpaMapper;

  @Override
  @Cacheable(cacheNames = CITIES)
  public List<CitiesView1> loadAllAsViews1() {
    return citySpringRepository.findAllAsViews1();
  }
}
