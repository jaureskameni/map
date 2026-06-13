package cm.klg.map.adapter.persistence.outbound.jpa;

import cm.klg.map.application.outbound.DistrictRepository;
import cm.klg.map.application.views.DistrictsView1;
import cm.klg.map.domain.city.CityId;
import cm.klg.map.domain.district.District;
import cm.klg.map.domain.district.DistrictId;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DistrictJpaRepository implements DistrictRepository {
  private final DistrictSpringRepository districtSpringRepository;
  private final JpaMapper jpaMapper;

  @Override
  public Optional<District> findById(DistrictId id) {
    return districtSpringRepository.findById(id.value()).map(jpaMapper::toDomain);
  }

  @Override
  public void save(District district) {
    districtSpringRepository.save(jpaMapper.toJpa(district));
  }

  @Override
  public List<DistrictsView1> loadByCityIdAsViews1(CityId cityId) {
    return districtSpringRepository.findByCityIdAsViews1(cityId.value());
  }
}
