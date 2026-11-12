package cm.klg.map.adapter.rest.inbound;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

import cm.klg.common.base.transaction.UseCaseExecutor;
import cm.klg.generated.map.adapter.rest.inbound.dto.CityDTO;
import cm.klg.map.application.usecase.GetAllCitiesUseCase;
import cm.klg.map.application.usecase.GetCityByIdUseCase;
import cm.klg.map.application.views.CitiesView1;
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
class CityControllerTest {

  @Mock private UseCaseExecutor useCaseExecutor;
  @Mock private RestMapper restMapper;
  @Mock private GetAllCitiesUseCase getAllCitiesUseCase;
  @Mock private GetCityByIdUseCase getCityByIdUseCase;

  @InjectMocks private CityController objectUnderTest;

  @BeforeEach
  void setUp() {
    when(useCaseExecutor.executeQuery(any()))
        .thenAnswer(invocation -> invocation.<Supplier<?>>getArgument(0).get());
  }

  @Test
  void getAllCities_shouldReturnOk_withCityList() {
    // Given
    var cityId = UUID.randomUUID();
    var view = new CitiesView1(cityId, "Douala", LocalDateTime.now());
    var dto = new CityDTO().id(cityId).name("Douala");

    when(getAllCitiesUseCase.execute()).thenReturn(List.of(view));
    when(restMapper.toCityDTO(view)).thenReturn(dto);

    // When
    var result =
        given()
            .standaloneSetup(objectUnderTest)
            .contentType(MediaType.APPLICATION_JSON)
            .when()
            .get("/cities")
            .then()
            .statusCode(OK.value())
            .extract()
            .body()
            .jsonPath()
            .getList(".", CityDTO.class);

    // Then
    assertThat(result).hasSize(1);
    assertThat(result.getFirst().getId()).isEqualTo(cityId);
    verify(getAllCitiesUseCase).execute();
    verify(restMapper).toCityDTO(view);
  }

  @Test
  void getCityById_shouldReturnOk_whenCityExists() {
    var cityId = UUID.randomUUID();
    var city =
        new cm.klg.map.domain.city.City(
            new CityId(cityId),
            cm.klg.map.domain.city.CityName.from("Douala"),
            new cm.klg.common.base.domain.CreatedAt(LocalDateTime.now()));
    when(getCityByIdUseCase.execute(new CityId(cityId))).thenReturn(city);
    when(restMapper.toCityDTO(city)).thenReturn(new CityDTO().id(cityId).name("Douala"));

    var result =
        given()
            .standaloneSetup(objectUnderTest)
            .when()
            .get("/cities/{cityId}", cityId)
            .then()
            .statusCode(OK.value())
            .extract()
            .as(CityDTO.class);

    assertThat(result.getId()).isEqualTo(cityId);
    verify(getCityByIdUseCase).execute(new CityId(cityId));
  }
}
