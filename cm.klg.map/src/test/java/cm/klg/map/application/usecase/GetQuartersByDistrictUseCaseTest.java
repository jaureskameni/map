package cm.klg.map.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cm.klg.map.application.outbound.QuarterRepository;
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
class GetQuartersByDistrictUseCaseTest {

  @Mock private QuarterRepository quarterRepository;
  @InjectMocks private GetQuartersByDistrictUseCase objectUnderTest;

  @Test
  void execute_shouldReturnQuartersForDistrict() {
    // Given
    var districtId = new DistrictId(UUID.randomUUID());
    var cityId = UUID.randomUUID();
    var views =
        List.of(
            new QuartersView1(
                UUID.randomUUID(), cityId, districtId.value(), "Akwa", LocalDateTime.now()));
    when(quarterRepository.loadByDistrictIdAsViews1(districtId)).thenReturn(views);

    // When
    var result = objectUnderTest.execute(districtId);

    // Then
    assertThat(result).isEqualTo(views);
    verify(quarterRepository).loadByDistrictIdAsViews1(districtId);
  }
}
