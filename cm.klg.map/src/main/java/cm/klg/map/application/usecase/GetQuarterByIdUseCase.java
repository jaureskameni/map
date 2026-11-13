package cm.klg.map.application.usecase;

import cm.klg.map.application.outbound.QuarterRepository;
import cm.klg.map.domain.quarter.Quarter;
import cm.klg.map.domain.quarter.QuarterId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetQuarterByIdUseCase {
  private final QuarterRepository quarterRepository;

  public Quarter execute(QuarterId quarterId) {
    return quarterRepository.load(quarterId);
  }
}
