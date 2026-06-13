package cm.klg.map.adapter.persistence.outbound.jpa;

import cm.klg.map.application.views.QuartersView1;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuarterSpringRepository extends JpaRepository<QuarterJpa, UUID> {

  @Query(
      "SELECT new cm.klg.map.application.views.QuartersView1(q.id, q.cityId, q.districtId, q.name,"
          + " q.createdAt) FROM QuarterJpa q WHERE q.districtId = :districtId")
  List<QuartersView1> findByDistrictIdAsViews1(UUID districtId);
}
