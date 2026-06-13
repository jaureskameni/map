package cm.klg.map.config;

import cm.klg.map.application.outbound.CityRepository;
import cm.klg.map.application.outbound.DistrictRepository;
import cm.klg.map.application.outbound.QuarterRepository;
import cm.klg.map.application.usecase.GetAllCitiesUseCase;
import cm.klg.map.application.usecase.GetDistrictsByCityUseCase;
import cm.klg.map.application.usecase.GetQuartersByDistrictUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapSpringBeans {

  @Bean
  public GetAllCitiesUseCase getAllCitiesUseCase(CityRepository cityRepository) {
    return new GetAllCitiesUseCase(cityRepository);
  }

  @Bean
  public GetDistrictsByCityUseCase getDistrictsByCityUseCase(
      DistrictRepository districtRepository) {
    return new GetDistrictsByCityUseCase(districtRepository);
  }

  @Bean
  public GetQuartersByDistrictUseCase getQuartersByDistrictUseCase(
      QuarterRepository quarterRepository) {
    return new GetQuartersByDistrictUseCase(quarterRepository);
  }
}
