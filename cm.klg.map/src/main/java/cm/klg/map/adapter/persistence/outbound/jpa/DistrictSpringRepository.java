package cm.klg.map.adapter.persistence.outbound.jpa;

import cm.klg.map.application.views.DistrictsView1;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DistrictSpringRepository extends JpaRepository<DistrictJpa, UUID> {

  @Query(
      "SELECT new cm.klg.map.application.views.DistrictsView1(d.id, d.cityId, d.name, d.createdAt)"
          + " FROM DistrictJpa d WHERE d.cityId = :cityId")
  List<DistrictsView1> findByCityIdAsViews1(UUID cityId);
}
