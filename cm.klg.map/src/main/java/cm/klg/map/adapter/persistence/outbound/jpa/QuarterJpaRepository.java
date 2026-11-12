package cm.klg.map.adapter.persistence.outbound.jpa;

import static cm.klg.map.utils.Constants.CacheKey.QUARTERS_BY_DISTRICT;

import cm.klg.map.application.outbound.QuarterRepository;
import cm.klg.map.application.views.QuartersView1;
import cm.klg.map.domain.district.DistrictId;
import cm.klg.map.domain.quarter.Quarter;
import cm.klg.map.domain.quarter.QuarterId;
import cm.klg.map.domain.quarter.QuarterNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

@RequiredArgsConstructor
public class QuarterJpaRepository implements QuarterRepository {
  private final QuarterSpringRepository quarterSpringRepository;
  private final JpaMapper jpaMapper;

  @Override
  @Cacheable(cacheNames = QUARTERS_BY_DISTRICT, key = "#districtId.value()")
  public List<QuartersView1> loadByDistrictIdAsViews1(DistrictId districtId) {
    return quarterSpringRepository.findByDistrictIdAsViews1(districtId.value());
  }

  @Override
  public Quarter load(QuarterId quarterId) {
    return quarterSpringRepository
        .findById(quarterId.value())
        .map(jpaMapper::toDomain)
        .orElseThrow(QuarterNotFoundException::new);
  }
}
