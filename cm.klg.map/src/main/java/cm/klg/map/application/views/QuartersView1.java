package cm.klg.map.application.views;

import java.time.LocalDateTime;
import java.util.UUID;

public record QuartersView1(
    UUID id, UUID cityId, UUID districtId, String name, LocalDateTime createdAt) {}
