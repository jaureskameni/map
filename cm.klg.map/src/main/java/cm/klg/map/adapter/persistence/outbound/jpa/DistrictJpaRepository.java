package cm.klg.map.adapter.persistence.outbound.jpa;

import static cm.klg.map.utils.Constants.CacheKey.DISTRICTS_BY_CITY;

import cm.klg.map.application.outbound.DistrictRepository;
import cm.klg.map.application.views.DistrictsView1;
import cm.klg.map.domain.city.CityId;
import cm.klg.map.domain.district.District;
import cm.klg.map.domain.district.DistrictId;
import cm.klg.map.domain.district.DistrictNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

@RequiredArgsConstructor
public class DistrictJpaRepository implements DistrictRepository {
  private final DistrictSpringRepository districtSpringRepository;
  private final JpaMapper jpaMapper;

  @Override
  @Cacheable(cacheNames = DISTRICTS_BY_CITY, key = "#cityId.value()")
  public List<DistrictsView1> loadByCityIdAsViews1(CityId cityId) {
    return districtSpringRepository.findByCityIdAsViews1(cityId.value());
  }

  @Override
  public District load(DistrictId districtId) {
    return districtSpringRepository
        .findById(districtId.value())
        .map(jpaMapper::toDomain)
        .orElseThrow(DistrictNotFoundException::new);
  }
}
