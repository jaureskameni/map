package cm.klg.map.domain.district;

import cm.klg.common.base.domain.CreatedAt;
import lombok.Getter;

@Getter
public class District {
  private DistrictId id;

  private CityId cityId;

  private DistrictName name;

  private CreatedAt createdAt;

  public District(DistrictId id, CityId cityId, DistrictName name, CreatedAt createdAt) {
    this.id = id;
    this.cityId = cityId;
    this.name = name;
    this.createdAt = createdAt;
  }
}
