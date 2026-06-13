package cm.klg.map.adapter.persistence.outbound.jpa;

import cm.klg.map.application.outbound.CityRepository;
import cm.klg.map.application.views.CitiesView1;
import cm.klg.map.domain.city.City;
import cm.klg.map.domain.city.CityId;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CityJpaRepository implements CityRepository {
  private final CitySpringRepository citySpringRepository;
  private final JpaMapper jpaMapper;

  @Override
  public Optional<City> findById(CityId id) {
    return citySpringRepository.findById(id.value()).map(jpaMapper::toDomain);
  }

  @Override
  public void save(City city) {
    citySpringRepository.save(jpaMapper.toJpa(city));
  }

  @Override
  public List<CitiesView1> loadAllAsViews1() {
    return citySpringRepository.findAllAsViews1();
  }
}
