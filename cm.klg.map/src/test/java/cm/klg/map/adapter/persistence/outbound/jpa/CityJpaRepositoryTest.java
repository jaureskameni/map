package cm.klg.map.adapter.persistence.outbound.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cm.klg.common.base.domain.CreatedAt;
import cm.klg.map.application.views.CitiesView1;
import cm.klg.map.domain.city.City;
import cm.klg.map.domain.city.CityId;
import cm.klg.map.domain.city.CityName;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
  void findById_shouldReturnCity_whenFound() {
    // Given
    var uuid = UUID.randomUUID();
    var cityId = new CityId(uuid);
    var cityJpa = new CityJpa();
    var city = new City(cityId, new CityName("Douala"), new CreatedAt(LocalDateTime.now()));

    when(citySpringRepository.findById(uuid)).thenReturn(Optional.of(cityJpa));
    when(jpaMapper.toDomain(cityJpa)).thenReturn(city);

    // When
    var result = objectUnderTest.findById(cityId);

    // Then
    assertThat(result).isPresent().contains(city);
    verify(citySpringRepository).findById(uuid);
    verify(jpaMapper).toDomain(cityJpa);
  }

  @Test
  void save_shouldCallSpringRepository() {
    // Given
    var city =
        new City(
            new CityId(UUID.randomUUID()),
            new CityName("Douala"),
            new CreatedAt(LocalDateTime.now()));
    var cityJpa = new CityJpa();

    when(jpaMapper.toJpa(city)).thenReturn(cityJpa);

    // When
    objectUnderTest.save(city);

    // Then
    verify(citySpringRepository).save(cityJpa);
    verify(jpaMapper).toJpa(city);
  }

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
