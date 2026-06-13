package cm.klg.map.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cm.klg.map.application.outbound.CityRepository;
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
class GetAllCitiesUseCaseTest {

  @Mock private CityRepository cityRepository;
  @InjectMocks private GetAllCitiesUseCase objectUnderTest;

  @Test
  void execute_shouldReturnAllCities() {
    // Given
    var views = List.of(new CitiesView1(UUID.randomUUID(), "Douala", LocalDateTime.now()));
    when(cityRepository.loadAllAsViews1()).thenReturn(views);

    // When
    var result = objectUnderTest.execute();

    // Then
    assertThat(result).isEqualTo(views);
    verify(cityRepository).loadAllAsViews1();
  }
}
