package cm.klg.map.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cm.klg.common.base.domain.CreatedAt;
import cm.klg.map.application.outbound.QuarterRepository;
import cm.klg.map.domain.quarter.CityId;
import cm.klg.map.domain.quarter.DistrictId;
import cm.klg.map.domain.quarter.Quarter;
import cm.klg.map.domain.quarter.QuarterId;
import cm.klg.map.domain.quarter.QuarterName;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetQuarterByIdUseCaseTest {
  @Mock private QuarterRepository quarterRepository;
  @InjectMocks private GetQuarterByIdUseCase objectUnderTest;

  @Test
  void execute_shouldReturnQuarterWhenItExists() {
    var quarterId = QuarterId.generate();
    var quarter =
        new Quarter(
            quarterId,
            new CityId(UUID.randomUUID()),
            new DistrictId(UUID.randomUUID()),
            QuarterName.from("Akwa"),
            new CreatedAt(LocalDateTime.now()));
    when(quarterRepository.load(quarterId)).thenReturn(quarter);

    assertThat(objectUnderTest.execute(quarterId)).isSameAs(quarter);
    verify(quarterRepository).load(quarterId);
  }
}
