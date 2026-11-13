package cm.klg.map.application.usecase;

import cm.klg.map.application.outbound.CityRepository;
import cm.klg.map.domain.city.City;
import cm.klg.map.domain.city.CityId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetCityByIdUseCase {
  private final CityRepository cityRepository;

  public City execute(CityId cityId) {
    return cityRepository.load(cityId);
  }
}
