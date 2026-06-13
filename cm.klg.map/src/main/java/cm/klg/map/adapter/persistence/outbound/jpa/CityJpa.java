package cm.klg.map.adapter.persistence.outbound.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@SuppressWarnings("JpaDataSourceORMInspection")
@FieldNameConstants
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_city")
public class CityJpa {

  @Id
  @Column(name = "c_id")
  private UUID id;

  @Column(name = "c_name")
  private String name;

  @Column(name = "c_created_at")
  private LocalDateTime createdAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CityJpa cityJpa = (CityJpa) o;
    return Objects.equals(id, cityJpa.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
