package cm.klg.map.adapter.rest.inbound;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

import cm.klg.common.base.transaction.UseCaseExecutor;
import cm.klg.generated.map.adapter.rest.inbound.dto.DistrictDTO;
import cm.klg.map.application.usecase.GetDistrictByIdUseCase;
import cm.klg.map.application.usecase.GetDistrictsByCityUseCase;
import cm.klg.map.application.views.DistrictsView1;
import cm.klg.map.domain.city.CityId;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

@ExtendWith(MockitoExtension.class)
class DistrictControllerTest {

  @Mock private UseCaseExecutor useCaseExecutor;
  @Mock private RestMapper restMapper;
  @Mock private GetDistrictsByCityUseCase getDistrictsByCityUseCase;
  @Mock private GetDistrictByIdUseCase getDistrictByIdUseCase;

  @InjectMocks private DistrictController objectUnderTest;

  @BeforeEach
  void setUp() {
    when(useCaseExecutor.executeQuery(any()))
        .thenAnswer(invocation -> invocation.<Supplier<?>>getArgument(0).get());
  }

  @Test
  void getDistrictsByCity_shouldReturnOk_withDistrictList() {
    // Given
    var cityId = UUID.randomUUID();
    var districtId = UUID.randomUUID();
    var view = new DistrictsView1(districtId, cityId, "Douala 1er", LocalDateTime.now());
    var dto = new DistrictDTO().id(districtId).cityId(cityId).name("Douala 1er");

    when(getDistrictsByCityUseCase.execute(new CityId(cityId))).thenReturn(List.of(view));
    when(restMapper.toDistrictDTO(view)).thenReturn(dto);

    // When
    var result =
        given()
            .standaloneSetup(objectUnderTest)
            .contentType(MediaType.APPLICATION_JSON)
            .when()
            .get("/cities/{cityId}/districts", cityId)
            .then()
            .statusCode(OK.value())
            .extract()
            .body()
            .jsonPath()
            .getList(".", DistrictDTO.class);

    // Then
    assertThat(result).hasSize(1);
    assertThat(result.getFirst().getId()).isEqualTo(districtId);
    verify(getDistrictsByCityUseCase).execute(new CityId(cityId));
    verify(restMapper).toDistrictDTO(view);
  }

  @Test
  void getDistrictById_shouldReturnOk_whenDistrictExists() {
    var districtId = UUID.randomUUID();
    var district =
        new cm.klg.map.domain.district.District(
            new cm.klg.map.domain.district.DistrictId(districtId),
            new cm.klg.map.domain.district.CityId(UUID.randomUUID()),
            cm.klg.map.domain.district.DistrictName.from("Douala 1er"),
            new cm.klg.common.base.domain.CreatedAt(LocalDateTime.now()));
    when(getDistrictByIdUseCase.execute(new cm.klg.map.domain.district.DistrictId(districtId)))
        .thenReturn(district);
    when(restMapper.toDistrictDTO(district))
        .thenReturn(new DistrictDTO().id(districtId).name("Douala 1er"));

    var result =
        given()
            .standaloneSetup(objectUnderTest)
            .when()
            .get("/districts/{districtId}", districtId)
            .then()
            .statusCode(OK.value())
            .extract()
            .as(DistrictDTO.class);

    assertThat(result.getId()).isEqualTo(districtId);
    verify(getDistrictByIdUseCase).execute(new cm.klg.map.domain.district.DistrictId(districtId));
  }
}
