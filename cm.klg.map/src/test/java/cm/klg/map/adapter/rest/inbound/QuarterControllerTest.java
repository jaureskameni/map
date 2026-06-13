package cm.klg.map.adapter.rest.inbound;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

import cm.klg.common.base.transaction.UseCaseExecutor;
import cm.klg.generated.map.adapter.rest.inbound.dto.QuarterDTO;
import cm.klg.map.application.usecase.GetQuartersByDistrictUseCase;
import cm.klg.map.application.views.QuartersView1;
import cm.klg.map.domain.district.DistrictId;
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
class QuarterControllerTest {

  @Mock private UseCaseExecutor useCaseExecutor;
  @Mock private RestMapper restMapper;
  @Mock private GetQuartersByDistrictUseCase getQuartersByDistrictUseCase;

  @InjectMocks private QuarterController objectUnderTest;

  @BeforeEach
  void setUp() {
    when(useCaseExecutor.executeQuery(any()))
        .thenAnswer(invocation -> invocation.<Supplier<?>>getArgument(0).get());
  }

  @Test
  void getQuartersByDistrict_shouldReturnOk_withQuarterList() {
    // Given
    var cityId = UUID.randomUUID();
    var districtId = UUID.randomUUID();
    var quarterId = UUID.randomUUID();
    var view = new QuartersView1(quarterId, cityId, districtId, "Akwa", LocalDateTime.now());
    var dto = new QuarterDTO().id(quarterId).cityId(cityId).districtId(districtId).name("Akwa");

    when(getQuartersByDistrictUseCase.execute(new DistrictId(districtId)))
        .thenReturn(List.of(view));
    when(restMapper.toQuarterDTO(view)).thenReturn(dto);

    // When
    var result =
        given()
            .standaloneSetup(objectUnderTest)
            .contentType(MediaType.APPLICATION_JSON)
            .when()
            .get("/districts/{districtId}/quarters", districtId)
            .then()
            .statusCode(OK.value())
            .extract()
            .body()
            .jsonPath()
            .getList(".", QuarterDTO.class);

    // Then
    assertThat(result).hasSize(1);
    assertThat(result.getFirst().getId()).isEqualTo(quarterId);
    verify(getQuartersByDistrictUseCase).execute(new DistrictId(districtId));
    verify(restMapper).toQuarterDTO(view);
  }
}
