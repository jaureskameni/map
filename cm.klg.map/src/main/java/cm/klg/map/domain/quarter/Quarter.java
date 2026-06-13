package cm.klg.map.domain.quarter;

import cm.klg.common.base.domain.CreatedAt;
import lombok.Getter;

@Getter
public class Quarter {
  private QuarterId id;

  private CityId cityId;

  private DistrictId districtId;

  private QuarterName name;

  private CreatedAt createdAt;

  public Quarter(
      QuarterId id, CityId cityId, DistrictId districtId, QuarterName name, CreatedAt createdAt) {
    this.id = id;
    this.cityId = cityId;
    this.districtId = districtId;
    this.name = name;
    this.createdAt = createdAt;
  }
}
