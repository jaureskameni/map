package cm.klg.map.adapter.persistence.outbound.jpa;

import cm.klg.map.application.outbound.QuarterRepository;
import cm.klg.map.application.views.QuartersView1;
import cm.klg.map.domain.district.DistrictId;
import cm.klg.map.domain.quarter.Quarter;
import cm.klg.map.domain.quarter.QuarterId;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuarterJpaRepository implements QuarterRepository {
  private final QuarterSpringRepository quarterSpringRepository;
  private final JpaMapper jpaMapper;

  @Override
  public Optional<Quarter> findById(QuarterId id) {
    return quarterSpringRepository.findById(id.value()).map(jpaMapper::toDomain);
  }

  @Override
  public void save(Quarter quarter) {
    quarterSpringRepository.save(jpaMapper.toJpa(quarter));
  }

  @Override
  public List<QuartersView1> loadByDistrictIdAsViews1(DistrictId districtId) {
    return quarterSpringRepository.findByDistrictIdAsViews1(districtId.value());
  }
}
