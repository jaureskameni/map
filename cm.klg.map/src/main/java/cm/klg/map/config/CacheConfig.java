package cm.klg.map.config;

import static cm.klg.map.utils.Constants.CacheKey.CITIES;
import static cm.klg.map.utils.Constants.CacheKey.DISTRICTS_BY_CITY;
import static cm.klg.map.utils.Constants.CacheKey.QUARTERS_BY_DISTRICT;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.time.Duration;
import java.util.List;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

  @Bean
  public CacheManager cacheManager() {
    var cacheManager = new CaffeineCacheManager();
    cacheManager.setCacheNames(List.of(CITIES, DISTRICTS_BY_CITY, QUARTERS_BY_DISTRICT));
    cacheManager.setCaffeine(
        Caffeine.newBuilder().maximumSize(1_000).expireAfterWrite(Duration.ofHours(12)));
    return cacheManager;
  }
}
