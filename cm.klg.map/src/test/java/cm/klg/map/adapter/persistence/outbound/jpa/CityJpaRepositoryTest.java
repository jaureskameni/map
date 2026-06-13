package cm.klg.map.adapter.persistence.outbound.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cm.klg.map.application.views.CitiesView1;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CityJpaRepositoryTest {

  @Mock private CitySpringRepository citySpringRepository;
  @Mock private JpaMapper jpaMapper;

  @InjectMocks private CityJpaRepository objectUnderTest;

  @Test
  void loadAllAsViews1_shouldReturnViews() {
    // Given
    var views = List.of(new CitiesView1(UUID.randomUUID(), "Douala", LocalDateTime.now()));
    when(citySpringRepository.findAllAsViews1()).thenReturn(views);

    // When
    var result = objectUnderTest.loadAllAsViews1();

    // Then
    assertThat(result).isEqualTo(views);
    verify(citySpringRepository).findAllAsViews1();
  }
}
