package cm.klg.map.domain.city;

import cm.klg.common.base.domain.CreatedAt;
import lombok.Getter;

@Getter
public class City {
  private CityId id;

  private CityName name;

  private CreatedAt createdAt;

  public City(CityId id, CityName name, CreatedAt createdAt) {
    this.id = id;
    this.name = name;
    this.createdAt = createdAt;
  }
}
