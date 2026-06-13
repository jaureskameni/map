package cm.klg.map.application.views;

import java.time.LocalDateTime;
import java.util.UUID;

public record DistrictsView1(UUID id, UUID cityId, String name, LocalDateTime createdAt) {}
