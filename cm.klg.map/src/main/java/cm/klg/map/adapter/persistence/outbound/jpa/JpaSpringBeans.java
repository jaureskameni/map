package cm.klg.map.adapter.persistence.outbound.jpa;

import cm.klg.map.application.outbound.CityRepository;
import cm.klg.map.application.outbound.DistrictRepository;
import cm.klg.map.application.outbound.QuarterRepository;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(
    basePackages = {
      "cm.klg.map.adapter.persistence.outbound.jpa",
    })
@EnableJpaRepositories(basePackages = {"cm.klg.map.adapter.persistence.outbound.jpa"})
public class JpaSpringBeans {

  @Bean
  public CityRepository cityRepository(
      CitySpringRepository citySpringRepository, JpaMapper jpaMapper) {
    return new CityJpaRepository(citySpringRepository, jpaMapper);
  }

  @Bean
  public DistrictRepository districtRepository(
      DistrictSpringRepository districtSpringRepository, JpaMapper jpaMapper) {
    return new DistrictJpaRepository(districtSpringRepository, jpaMapper);
  }

  @Bean
  public QuarterRepository quarterRepository(
      QuarterSpringRepository quarterSpringRepository, JpaMapper jpaMapper) {
    return new QuarterJpaRepository(quarterSpringRepository, jpaMapper);
  }
}
