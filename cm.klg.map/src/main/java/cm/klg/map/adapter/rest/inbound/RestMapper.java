package cm.klg.map.adapter.rest.inbound;

import cm.klg.generated.map.adapter.rest.inbound.dto.CityDTO;
import cm.klg.generated.map.adapter.rest.inbound.dto.DistrictDTO;
import cm.klg.generated.map.adapter.rest.inbound.dto.QuarterDTO;
import cm.klg.map.application.views.CitiesView1;
import cm.klg.map.application.views.DistrictsView1;
import cm.klg.map.application.views.QuartersView1;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestMapper {

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "createdAt", source = "createdAt")
  CityDTO toCityDTO(CitiesView1 citiesView1);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "cityId", source = "cityId")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "createdAt", source = "createdAt")
  DistrictDTO toDistrictDTO(DistrictsView1 districtsView1);

  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "cityId", source = "cityId")
  @Mapping(target = "districtId", source = "districtId")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "createdAt", source = "createdAt")
  QuarterDTO toQuarterDTO(QuartersView1 quartersView1);
}
