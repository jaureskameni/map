package cm.klg.map.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cm.klg.map.application.outbound.DistrictRepository;
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
class GetDistrictsByCityUseCaseTest {

  @Mock private DistrictRepository districtRepository;
  @InjectMocks private GetDistrictsByCityUseCase objectUnderTest;

  @Test
  void execute_shouldReturnDistrictsForCity() {
    // Given
    var cityId = new CityId(UUID.randomUUID());
    var views =
        List.of(
            new DistrictsView1(
                UUID.randomUUID(), cityId.value(), "Douala 1er", LocalDateTime.now()));
    when(districtRepository.loadByCityIdAsViews1(cityId)).thenReturn(views);

    // When
    var result = objectUnderTest.execute(cityId);

    // Then
    assertThat(result).isEqualTo(views);
    verify(districtRepository).loadByCityIdAsViews1(cityId);
  }
}
