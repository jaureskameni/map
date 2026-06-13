package cm.klg.map.adapter.persistence.outbound.jpa;

import cm.klg.common.base.domain.CreatedAt;
import cm.klg.map.domain.city.City;
import cm.klg.map.domain.city.CityId;
import cm.klg.map.domain.city.CityName;
import cm.klg.map.domain.district.District;
import cm.klg.map.domain.district.DistrictId;
import cm.klg.map.domain.district.DistrictName;
import cm.klg.map.domain.quarter.Quarter;
import cm.klg.map.domain.quarter.QuarterName;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JpaMapper {

  @Mapping(target = "id", source = "id.value")
  @Mapping(target = "name", source = "name.value")
  @Mapping(target = "createdAt", source = "createdAt.value")
  CityJpa toJpa(City city);

  default City toDomain(CityJpa cityJpa) {
    return new City(
        new CityId(cityJpa.getId()),
        new CityName(cityJpa.getName()),
        new CreatedAt(cityJpa.getCreatedAt()));
  }

  @Mapping(target = "id", source = "id.value")
  @Mapping(target = "cityId", source = "cityId.value")
  @Mapping(target = "name", source = "name.value")
  @Mapping(target = "createdAt", source = "createdAt.value")
  DistrictJpa toJpa(District district);

  default District toDomain(DistrictJpa districtJpa) {
    return new District(
        new DistrictId(districtJpa.getId()),
        new cm.klg.map.domain.district.CityId(districtJpa.getCityId()),
        new DistrictName(districtJpa.getName()),
        new CreatedAt(districtJpa.getCreatedAt()));
  }

  @Mapping(target = "id", source = "id.value")
  @Mapping(target = "cityId", source = "cityId.value")
  @Mapping(target = "districtId", source = "districtId.value")
  @Mapping(target = "name", source = "name.value")
  @Mapping(target = "createdAt", source = "createdAt.value")
  QuarterJpa toJpa(Quarter quarter);

  default Quarter toDomain(QuarterJpa quarterJpa) {
    return new Quarter(
        // Assuming QuarterId exists or will be fixed to use a proper ID type
        new cm.klg.map.domain.quarter.QuarterId(quarterJpa.getId()),
        new cm.klg.map.domain.quarter.CityId(quarterJpa.getCityId()),
        new cm.klg.map.domain.quarter.DistrictId(quarterJpa.getDistrictId()),
        new QuarterName(quarterJpa.getName()),
        new CreatedAt(quarterJpa.getCreatedAt()));
  }
}
