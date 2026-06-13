package cm.klg.map.adapter.persistence.outbound.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cm.klg.map.application.views.QuartersView1;
import cm.klg.map.domain.district.DistrictId;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QuarterJpaRepositoryTest {

  @Mock private QuarterSpringRepository quarterSpringRepository;
  @Mock private JpaMapper jpaMapper;

  @InjectMocks private QuarterJpaRepository objectUnderTest;

  @Test
  void loadByDistrictIdAsViews1_shouldReturnViews() {
    // Given
    var districtId = new DistrictId(UUID.randomUUID());
    var views =
        List.of(
            new QuartersView1(
                UUID.randomUUID(),
                UUID.randomUUID(),
                districtId.value(),
                "Akwa",
                LocalDateTime.now()));
    when(quarterSpringRepository.findByDistrictIdAsViews1(districtId.value())).thenReturn(views);

    // When
    var result = objectUnderTest.loadByDistrictIdAsViews1(districtId);

    // Then
    assertThat(result).isEqualTo(views);
    verify(quarterSpringRepository).findByDistrictIdAsViews1(districtId.value());
  }
}
