package cm.klg.map.adapter.persistence.outbound.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cm.klg.map.application.views.DistrictsView1;
import cm.klg.map.domain.city.CityId;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DistrictJpaRepositoryTest {

  @Mock private DistrictSpringRepository districtSpringRepository;
  @Mock private JpaMapper jpaMapper;

  @InjectMocks private DistrictJpaRepository objectUnderTest;

  @Test
  void loadByCityIdAsViews1_shouldReturnViews() {
    // Given
    var cityId = new CityId(UUID.randomUUID());
    var views =
        List.of(
            new DistrictsView1(
                UUID.randomUUID(), cityId.value(), "Douala 1er", LocalDateTime.now()));
    when(districtSpringRepository.findByCityIdAsViews1(cityId.value())).thenReturn(views);

    // When
    var result = objectUnderTest.loadByCityIdAsViews1(cityId);

    // Then
    assertThat(result).isEqualTo(views);
    verify(districtSpringRepository).findByCityIdAsViews1(cityId.value());
  }
}
