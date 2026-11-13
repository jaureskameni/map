package cm.klg.map.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cm.klg.common.base.domain.CreatedAt;
import cm.klg.map.application.outbound.CityRepository;
import cm.klg.map.domain.city.City;
import cm.klg.map.domain.city.CityId;
import cm.klg.map.domain.city.CityName;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetCityByIdUseCaseTest {
  @Mock private CityRepository cityRepository;
  @InjectMocks private GetCityByIdUseCase objectUnderTest;

  @Test
  void execute_shouldReturnCityWhenItExists() {
    var cityId = CityId.generate();
    var city = new City(cityId, CityName.from("Douala"), new CreatedAt(LocalDateTime.now()));
    when(cityRepository.load(cityId)).thenReturn(city);

    assertThat(objectUnderTest.execute(cityId)).isSameAs(city);
    verify(cityRepository).load(cityId);
  }
}
