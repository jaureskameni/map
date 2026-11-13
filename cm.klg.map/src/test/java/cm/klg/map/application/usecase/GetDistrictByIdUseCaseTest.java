package cm.klg.map.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cm.klg.common.base.domain.CreatedAt;
import cm.klg.map.application.outbound.DistrictRepository;
import cm.klg.map.domain.district.CityId;
import cm.klg.map.domain.district.District;
import cm.klg.map.domain.district.DistrictId;
import cm.klg.map.domain.district.DistrictName;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetDistrictByIdUseCaseTest {
  @Mock private DistrictRepository districtRepository;
  @InjectMocks private GetDistrictByIdUseCase objectUnderTest;

  @Test
  void execute_shouldReturnDistrictWhenItExists() {
    var districtId = DistrictId.generate();
    var district =
        new District(
            districtId,
            new CityId(UUID.randomUUID()),
            DistrictName.from("Douala 1er"),
            new CreatedAt(LocalDateTime.now()));
    when(districtRepository.load(districtId)).thenReturn(district);

    assertThat(objectUnderTest.execute(districtId)).isSameAs(district);
    verify(districtRepository).load(districtId);
  }
}
