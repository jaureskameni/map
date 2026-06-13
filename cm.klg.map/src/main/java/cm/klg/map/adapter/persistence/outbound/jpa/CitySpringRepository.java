package cm.klg.map.adapter.persistence.outbound.jpa;

import cm.klg.map.application.views.CitiesView1;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CitySpringRepository extends JpaRepository<CityJpa, UUID> {

  @Query(
      "SELECT new cm.klg.map.application.views.CitiesView1(c.id, c.name, c.createdAt) FROM CityJpa"
          + " c")
  List<CitiesView1> findAllAsViews1();
}
